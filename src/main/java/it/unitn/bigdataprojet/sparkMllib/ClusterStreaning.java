package it.unitn.bigdataprojet.sparkMllib;

import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.StreamingKMeans;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;


public final class ClusterStreaning {


	private static final Pattern SPACE = Pattern.compile(" ");
	private static JavaStreamingContext jsc;
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("SparkClusterStreaming").setMaster("spark://quickstart.cloudera:7077");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		jsc = new JavaStreamingContext(sparkConf, new Duration(2000));
		try {



			JavaDStream<Vector> inputSream =  jsc.textFileStream("hdfs://quickstart.cloudera:8020//user/cloudera/inputCluster/").map
					(new Function<String,Vector >() {
						public Vector call(String record) throws Exception {
							String[] tok = SPACE.split(record);
							double[] point = new double[tok.length];
							for (int i = 0; i < tok.length; ++i) {
								point[i] = Double.parseDouble(tok[i]);
							}
							return Vectors.dense(point);
						}});
			
			JavaDStream<Tuple2<Integer, Vector>> outPut = jsc.textFileStream("hdfs://quickstart.cloudera:8020//user/cloudera/outputCluster/").map
					(new Function<String,Tuple2<Integer, Vector> >() {
						public Tuple2<Integer, Vector> call(String record) throws Exception {
							String[] tok = SPACE.split(record);
							double[] point = new double[tok.length];
							for (int i = 0; i < tok.length; ++i) {
								point[i] = Double.parseDouble(tok[i]);
							}
							
							return new Tuple2<>(0,Vectors.dense(point));
						}});
			
			
			
			JavaPairDStream<Integer,Vector> outputSream =JavaPairDStream.fromJavaDStream(outPut);
					
					
				
							
				
			System.out.println("ProjectBigData - Vector");
			StreamingKMeans skmeans = new StreamingKMeans()
					.setK(10)
					.setDecayFactor(1.0)
					.setInitialCenters(new Vector[]{Vectors.dense(0.0)}, new double[]{0.0});

			skmeans.trainOn(inputSream);

			skmeans.predictOnValues(outputSream).saveAsHadoopFiles("hdfs://quickstart.cloudera:8020/user/cloudera/labeledCluster/","dir");
			 						
			jsc.start();
			jsc.awaitTermination();
			
		} catch (Exception e) {
			System.exit(1);
		}

		sc.stop();
		sc.close();
		System.out.println("ProjectBigData - End");
		System.exit(0);
	}
}
package it.unitn.bigdataprojet.sparkMllib;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;


public final class Collect_Local {


	public static Integer normaliseGlusterLactose(String line  ){		
		if (null != line){
			line = line.replace('"',' ').trim();
			if (line.equalsIgnoreCase("S")){
				return 1;
			}
		}
		return 0;
	}
	public static Integer normaliseSex(String line  ){		
		if (null != line){
			line = line.replace('"',' ').trim();
			if (line.equalsIgnoreCase("M")){
				return 1;
			}
		}
		return 0;
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("SparkMllibSql").setMaster("local[*]");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		try {
			SQLContext sqlContext = new org.apache.spark.sql.SQLContext(sc);
			//@SuppressWarnings("unchecked")
			JavaRDD<Administration> admin =  sc.textFile("files_test/text.txt").map(
					new Function<String, Administration>() {
						public Administration call(String line) throws Exception {
							String[] parts = line.split("	");
							//000000467	M	80	A	A02	A02B	A02BC		S
							Administration admin = new Administration(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
							try {
								admin.setPerson(Integer.valueOf(parts[0]));
								admin.setSex(normaliseSex(parts[1]));
								admin.setAge(Integer.valueOf(parts[2]));
								admin.setBefore30(admin.getAge() < 30 ? 1 : 0);
								admin.setAfter60(admin.getAge() > 60 ? 1 : 0);
								admin.setAge3060((admin.getBefore30() ==0 && admin.getAfter60()==0) ? 1 : 0);
								admin.setLactose(normaliseGlusterLactose(parts[7]));
								admin.setGluten(normaliseGlusterLactose(parts[8]));

								//level 1
								switch (parts[3]) {
								case "A":
									admin.setA(1);
									break;
								case "B":
									admin.setB(1);
									break;
								case "C":
									admin.setC(1);
									break;
								case "D":
									admin.setD(1);
									break;
								case "G":
									admin.setG(1);
									break;
								case "H":
									admin.setH(1);
									break;
								case "J":
									admin.setJ(1);
									break;
								case "L":
									admin.setL(1);
									break;
								case "M":
									admin.setM(1);
									break;
								case "N":
									admin.setN(1);
									break;
								case "P":
									admin.setP(1);
									break;
								case "R":
									admin.setR(1);
									break;
								case "S":
									admin.setS(1);
									break;
								case "V":
									admin.setV(1);
									break;


								default:
									break;
								}
								if (parts[5].equalsIgnoreCase("N04B"))
									admin.setN04B(1);

								if (parts[5].equalsIgnoreCase("A10A"))
									admin.setA10A(1);


								if (parts[6].equalsIgnoreCase("N05AA"))
									admin.setN05AA(1);

								if (parts[6].equalsIgnoreCase("N05AB"))
									admin.setN05AB(1);

								if (parts[6].equalsIgnoreCase("N05AC"))
									admin.setN05AC(1);

								if (parts[6].equalsIgnoreCase("N05AD"))
									admin.setN05AD(1);

								if (parts[6].equalsIgnoreCase("N05AF"))
									admin.setN05AF(1);

								if (parts[6].equalsIgnoreCase("N05AG"))
									admin.setN05AG(1);


								System.out.println(admin.toString());
							} catch (Exception e) {
								e.printStackTrace();
							}

							return admin;
						}
					});

			DataFrame schemaPeople = sqlContext.createDataFrame(admin, Administration.class);
			schemaPeople.registerTempTable("Administration");
			schemaPeople.show();	
			DataFrame pivout =	schemaPeople.groupBy("person").max("a","a10A","after60","age","age3060","b","before30","c","d","g","gluten","h",
					"j","l","lactose","m","n","n04B","n05AA","n05AB","n05AC","n05AD","n05AF","n05AG","p","r","s","sex","v");

			pivout.show();

			NaiveBayesModel model_create = null;
			try {
			//	model_create = NaiveBayesModel.load(sc.sc() , "files/models/NaiveBayes");
			} catch (Exception e) {
				System.out.println("ProjectBigData - Don't found NaiveBayes Model");
				model_create = null;
			}

			if (null == model_create){

				// prog=60 years, N04B(Parkinson),A10A(antidiabetes drugs),N05AA,N05AB,N05AC,N05AD,N05AF,N05AG
				final List<LabeledPoint> modelo = Arrays.asList(
						new LabeledPoint(100, Vectors.dense(1, 1, 1, 1, 1, 1, 1, 1, 1)),
						new LabeledPoint(99, Vectors.dense(1, 1, 0, 0, 0, 0, 0, 0, 0)),
						new LabeledPoint(95,  Vectors.dense(1, 1, 1, 1, 1, 1, 1, 0, 0)),
						new LabeledPoint(93,  Vectors.dense(0, 1, 1, 1, 1, 0, 0, 1, 1)),
						new LabeledPoint(90,  Vectors.dense(1, 1, 0, 0, 1, 1, 1, 1, 1)),
						new LabeledPoint(85,  Vectors.dense(0, 0, 1, 1, 1, 1, 1, 1, 1)),
						new LabeledPoint(85,  Vectors.dense(0, 0, 0, 1, 1, 1, 1, 1, 1)),				
						new LabeledPoint(60,  Vectors.dense(0, 0, 1, 0, 0, 1, 1, 0, 0)),
						new LabeledPoint(55,  Vectors.dense(1, 0, 0, 1, 0, 0, 0, 0, 1)),
						new LabeledPoint(50,  Vectors.dense(1, 0, 1, 0, 0, 0, 0, 0, 0)),
						new LabeledPoint(40,  Vectors.dense(0, 0, 1, 0, 1, 0, 1, 0, 1)),
						new LabeledPoint(20,  Vectors.dense(0, 0, 1, 0, 0, 0, 0, 0, 0)),
						new LabeledPoint(5,  Vectors.dense(1, 0, 0, 0, 0, 0, 0, 0, 0)),
						new LabeledPoint(0,   Vectors.dense(0, 0, 0, 0, 0, 0, 0, 0, 0))		
						);

				JavaRDD<LabeledPoint> training = sc.parallelize(modelo, 2).cache();

				training.cache(); 

				model_create = NaiveBayes.train(training.rdd(), 1.0, "bernoulli");
				//model_create.save(sc.sc() , "NaiveBayes");
				System.out.println("ProjectBigData - Salved NaiveBayes Model");
			}else{
				System.out.println("ProjectBigData - found NaiveBayes Model");
			}
			final NaiveBayesModel model = model_create;



			JavaRDD<Vector> cluster = pivout.toJavaRDD().map(
					new Function<Row, Vector>() {
						public Vector call(Row record) throws Exception {

							/*
							 * 0 StructType(StructField(person,IntegerType,false), 
							 * 1 StructField(max(a),IntegerType,true), 
							 * 2 StructField(max(a10A),IntegerType,true), 
							 * 3 StructField(max(after60),IntegerType,true), 
							 * 4 StructField(max(age),IntegerType,true), 
							 * 5 StructField(max(age3060),IntegerType,true), 
							 * 6 StructField(max(b),IntegerType,true), 
							 * 7 StructField(max(before30),IntegerType,true), 
							 * 8 StructField(max(c),IntegerType,true), 
							 * 9 StructField(max(d),IntegerType,true), 
							 * 10 StructField(max(g),IntegerType,true), 
							 * 11 StructField(max(gluten),IntegerType,true), 
							 * 12 StructField(max(h),IntegerType,true), 
							 * 13 StructField(max(j),IntegerType,true), 
							 * 14 StructField(max(l),IntegerType,true), 
							 * 15 StructField(max(lactose),IntegerType,true), 
							 * 16 StructField(max(m),IntegerType,true), 
							 * 17 StructField(max(n),IntegerType,true), 
							 * 18 StructField(max(n04B),IntegerType,true), 
							 * 19 StructField(max(n05AA),IntegerType,true), 
							 * 20 StructField(max(n05AB),IntegerType,true), 
							 * 21 StructField(max(n05AC),IntegerType,true), 
							 * 22 StructField(max(n05AD),IntegerType,true), 
							 * 23 StructField(max(n05AF),IntegerType,true), 
							 * 24 StructField(max(n05AG),IntegerType,true), 
							 * 25 StructField(max(p),IntegerType,true), 
							 * 26 StructField(max(r),IntegerType,true), 
							 * 27 StructField(max(s),IntegerType,true), 
							 * 28 StructField(max(sex),IntegerType,true), 
							 * 29 StructField(max(v),IntegerType,true))
							 */
							//prog=60 years(5), N04B(Parkinson)(18),A10A(antidiabetes drugs)(2),N05AA(19),N05AB(20),N05AC(21),N05AD(22),N05AF(23),N05AG(24)
							double predict =  model.predict(Vectors.dense(record.getInt(5),record.getInt(18),record.getInt(2),record.getInt(19),record.getInt(20),record.getInt(21),
									record.getInt(22),record.getInt(23),record.getInt(24)));	

							Vector dv = Vectors.dense(predict,record.getInt(1),record.getInt(2),record.getInt(3),record.getInt(4),record.getInt(5),record.getInt(6),record.getInt(7),
									record.getInt(8),record.getInt(9),record.getInt(10),record.getInt(11),record.getInt(12),record.getInt(13),record.getInt(14),record.getInt(15),
									record.getInt(16),record.getInt(17),record.getInt(18),record.getInt(19),record.getInt(20),record.getInt(21),record.getInt(22),record.getInt(23),
									record.getInt(24),record.getInt(25),record.getInt(26),record.getInt(27),record.getInt(28),record.getInt(29));
							
							return dv;
						}
					});

			int numClusters = 7;
			int numIterations = 80;
			System.out.println("ProjectBigData - Vector");
			System.out.println(cluster.rdd().toString());
			final KMeansModel clusters = KMeans.train(cluster.rdd(), numClusters, numIterations);
			

			//Long time = System.currentTimeMillis();
			//clusters.save(sc.sc(), "modelCluster"+Long.toString(time));
			System.out.println("ProjectBigData - Cluster model salved");
			

			double error = clusters.computeCost(cluster.rdd());
			JavaRDD<String> result = cluster.map(new Function<Vector, String>() {
				public String call(Vector record) throws Exception {
					int val = clusters.predict(record);			
					return   ""+val+"," +record.toString().replace('[', ' ').toString().replace(']', ' ');
				}}).repartition(1);

			System.out.println("Within Set Sum of Squared Errors = " + error);
			result.saveAsTextFile("files_test/clusters.txt");
		} catch (Exception e) {
			System.exit(1);
		}

		sc.stop();
		sc.close();
		System.out.println("ProjectBigData - End");
		System.exit(0);
	}
}

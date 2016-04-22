import java.io.PrintWriter

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

object UserKMeans {
	def main(args: Array[String]) {
		val dataPath = "./data/clustering/"
		//System.setProperty("hadoop.home.dir", "C:\\Users\\wyq\\Desktop\\spark\\hadoop");
		val conf = new SparkConf().setAppName("User k-means").setMaster("local[*]")
		val sc = new SparkContext(conf)

		/**
			* Clustering user
			*/
		// Load and parse the data
		val user_input_dir = dataPath + "userClusteringData"
		val user_output_dir = dataPath + "userClusteringResult/"

		val userData = sc.textFile(user_input_dir)

    val parsedUserData = userData.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()

		// Cluster the user data into 50 classes using KMeans
		val userNumClusters = 3
		val userNumIterations = 20
		val userClusters = KMeans.train(parsedUserData, userNumClusters, userNumIterations)

		val res = parsedUserData.map(v => (userClusters.predict(v), v)).collect()
//		res.saveAsTextFile(user_output_dir)
		val res_out = new PrintWriter(user_output_dir + "user_result.txt")
		res.foreach(res_out.println)
		res_out.close()

		val out = new PrintWriter(user_output_dir + "user_centers.txt")
		val centers = userClusters.clusterCenters
		centers.foreach(out.println)
		out.close()

		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = userClusters.computeCost(parsedUserData)
		println("Within Set Sum of Squared Errors = " + WSSSE)

	}
}

import java.io.PrintWriter

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

object UserKMeans {
	val dataPath = "./data/clustering/"
	val conf = new SparkConf().setAppName("User k-means").setMaster("local[*]")
	val sc = new SparkContext(conf)

	val user_input_path = dataPath + "userClusteringData"
	val user_output_dir = dataPath + "userClusteringResult"


	def main(args: Array[String]) {
		kmeans(65,200)
	}

	def kmeans(userNumClusters: Int, userNumIterations: Int)= {
		val userData = sc.textFile(user_input_path)

    val parsedUserData = userData.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()

		// Cluster the user data into 50 classes using KMeans
		val userClusters = KMeans.train(parsedUserData, userNumClusters, userNumIterations)

		val res = parsedUserData.map(v => (userClusters.predict(v), v)).collect()
//		res.saveAsTextFile(user_output_dir)
		val res_out = new PrintWriter(user_output_dir)
		res.foreach(res_out.println)
		res_out.close()

//		val out = new PrintWriter(user_output_dir + "user_centers.txt")
//		val centers = userClusters.clusterCenters
//		centers.foreach(out.println)
//		out.close()

		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = userClusters.computeCost(parsedUserData)
		println("# of cluster: " + userNumClusters + ", # of iterations:" + userNumIterations + ", WSSSE:" + WSSSE)

	}
}

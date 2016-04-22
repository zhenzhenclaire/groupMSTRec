import java.io.PrintWriter

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

object ItemKMeans {
	def main(args: Array[String]) {
		val dataPath = "/data/clustering/"
		//System.setProperty("hadoop.home.dir", "C:\\Users\\wyq\\Desktop\\spark\\hadoop");
		val conf = new SparkConf().setAppName("Item K-means").setMaster("local[*]")
		val sc = new SparkContext(conf)

		/**
			* Clustering item
			*/
		val item_input_dir = dataPath + "itemClusteringData"
		val item_output_dir = dataPath + "itemClusteringResult"

		val itemData = sc.textFile(item_input_dir)
		val parsedItemData = itemData.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()

		// Cluster the item data into 30 classes using KMeans
		val itemNumClusters = 3
		val itemNumIterations = 20
		val itemClusters = KMeans.train(parsedItemData, itemNumClusters, itemNumIterations)

		val res = parsedItemData.map(v => (itemClusters.predict(v), v))
		res.saveAsTextFile(item_output_dir)
		val res_out = new PrintWriter("item_result.txt")
		res.foreach(res_out.println)
		res_out.close()

		val out = new PrintWriter("item_centers.txt")
		val centers = itemClusters.clusterCenters
		centers.foreach(out.println)
		out.close()

		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = itemClusters.computeCost(parsedItemData)
		println("Within Set Sum of Squared Errors = " + WSSSE)
	}
}

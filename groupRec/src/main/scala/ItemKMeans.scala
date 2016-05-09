import java.io.PrintWriter


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.broadcast.Broadcast



object ItemKMeans {
	val dataPath = "./data/clustering/"

	val conf = new SparkConf().setAppName("Item K-means").setMaster("local[*]")
	val sc = new SparkContext(conf)

	val item_input_path = dataPath + "itemClusteringData"
	//val item_reflect_dir = dataPath + "itemReflectData/"
	val item_reflect_dir = dataPath + "itemClusteringDataTest"
	val item_output_dir = dataPath + "itemClusteringResult"

	def main(args: Array[String]) {
//		refelct()
		kmeans(1250,200)
	}

	/**
		* Reflect n-th dimension and replace the attribute with reflected num
    */
	def refelct(): Unit ={
		val strText = sc.textFile(item_input_path)

		val stateMap = makeFeaIdMap(2)
		val NoiseMap = makeFeaIdMap(5)
		val AlcoholMap = makeFeaIdMap(7)

		val broadcastStateMap = sc.broadcast(stateMap.toMap)
		val broadcastNoiseMap = sc.broadcast(NoiseMap.toMap)
		val broadcastAlhMap = sc.broadcast(AlcoholMap.toMap)

		strText.map(x => (replaceFeaWithID(x, broadcastStateMap,broadcastNoiseMap,broadcastAlhMap)))
				.coalesce(1, shuffle = false)
				.saveAsTextFile(item_reflect_dir)
	}

	def replaceFea(line:String,fea1IdMap:Broadcast[Map[String,Int]]):String={
		val t = line.split(",")
		var res = ""
		// Skip the first attribute business_id
		val i = 1;
		res += t(i)+ ","
		res += fea1IdMap.value(t(1 + i))+ ","
		res += t(2 + i)
		res
	}

	def replaceFeaWithID(line:String,fea1IdMap:Broadcast[Map[String,Int]],fea2IdMap:Broadcast[Map[String,Int]],fea3IdMap:Broadcast[Map[String,Int]]):String={
		val t = line.split(",")
		var res = ""
		// Skip the first attribute business_id
		val i = 1;
		res += t(i)+ ","
		res += fea1IdMap.value(t(1 + i))+ ","
		res += t(2 + i)+ ","
		res += getValue(t(3 + i))+ ","
		res += fea2IdMap.value(t(4 + i)) + ","
		res += getValue(t(5 + i))+ ","
		res += fea3IdMap.value(t(6 + i)) + ","
		res += getValue(t(7 + i))+ ","
		res += getValue(t(8 + i))
		//res += t(9)
		res
	}

	def getValue(line:String):String={
		if(line == null){
			"-1"
		}else if("true".equals(line)){
			"1"
		}else if("false".equals(line)){
			"0"
		}else{
			"-1"
		}
	}

	/**
		* Create feature attributes to map
		* @param n
		* @return
    */
	def makeFeaIdMap(n: Int) :Array[(String,Int)] = {
		val strText = sc.textFile(item_input_path)

		val features = strText.map(x => {
			val t = x.split(",")
			(t(n),1)
		}).reduceByKey(_ + _).map(_._1).collect()
		val ids = Array(0 until features.length: _*)
		val feaIdMap = features.zip(ids)//.toMap
		println("feature length:" + features.count(x => true))
		feaIdMap
	}

	def kmeans(itemNumClusters: Int, itemNumIterations: Int)= {
		/**
			* Clustering item
			*/
		val itemData = sc.textFile(item_reflect_dir)
		val parsedItemData = itemData.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()

		// Cluster the item data into 30 classes using KMeans
		// Within Set Sum of Squared Errors = 4047962.0736539997
		val itemClusters = KMeans.train(parsedItemData, itemNumClusters, itemNumIterations)

		val res = parsedItemData.map(v => (itemClusters.predict(v), v)).collect()

		val res_out = new PrintWriter(item_output_dir)
		res.foreach(res_out.println)
		res_out.close()

//		val out = new PrintWriter("item_centers.txt")
//		val centers = itemClusters.clusterCenters
//		centers.foreach(out.println)
//		out.close()


		// Evaluate clustering by computing Within Set Sum of Squared Errors
		val WSSSE = itemClusters.computeCost(parsedItemData)
		println("# of cluster: " + itemNumClusters + ", # of iterations:" + itemNumIterations + ", WSSSE:" + WSSSE)
	}
}

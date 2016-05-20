package com.claire.util;

import org.jgrapht.Graph;
import org.jgrapht.ext.GraphMLExporter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerConfigurationException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by claire on 5/20/16.
 */
public class GraphProcess {

    public static void graphExport(String oPath, Graph g){
        try {
            GraphMLExporter exporter = new GraphMLExporter<>();
            Writer write = new BufferedWriter(new FileWriter(oPath));
            exporter.export(write,g);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static void saveWeightedGraph(String oPath, Graph g){
        Set<DefaultWeightedEdge> edgesets  = g.edgeSet();
        try {
            Writer write = new BufferedWriter(new FileWriter(oPath));
            Iterator<DefaultWeightedEdge> it = edgesets.iterator();
            while (it.hasNext()) {
                DefaultWeightedEdge e = it.next();
                String line = e.toString();
                line = line.replaceAll("\\(","");
                line = line.replaceAll("\\)","");
                line = line.replaceAll(" ","");
                String userNode = line.split(":")[0];
                String itemNode = line.split(":")[1];

                String graph = g.getEdge(userNode,itemNode).toString() + ":" +g.getEdgeWeight(g.getEdge(userNode,itemNode));
                write.write(graph + "\n");
            }
            write.flush();
            write.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}

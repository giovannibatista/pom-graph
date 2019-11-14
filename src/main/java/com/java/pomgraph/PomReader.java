package com.java.pomgraph;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class PomReader {

	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("/Users/giovannicarlos/dev/pom-utils/cer/CER.App.Java.Cert-Procergs/pom.xml"));
        System.out.println(model.getId());
        System.out.println(model.getGroupId());
        System.out.println(model.getArtifactId());
        System.out.println(model.getVersion());
        System.out.println(model.getDependencies().toString());
        
        Gav gav = new Gav.Builder().setGroupId(model.getGroupId()).setArtifactId(model.getArtifactId()).setVersion(model.getVersion()).build();
        
        System.out.println(gav.toString());

	}

}

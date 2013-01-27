package cz.nkp.differ.io;

import cz.nkp.differ.compare.io.SerializableImageProcessorResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 *
 * @author xrosecky
 */
public class ResultManager {

    private static final String EXTENSION = ".xml";
    private Jaxb2Marshaller marshaller;
    private String directory;

    public void save(SerializableImageProcessorResult result) throws IOException {
	String name = new Date().toString();
	File outputFile = new File(directory, name + EXTENSION);
	OutputStream os;
	os = new FileOutputStream(outputFile);
	StreamResult streamResult = new StreamResult(os);
	marshaller.marshal(result, streamResult);
    }

    public List<String> getResults() {
	List<String> result = new ArrayList<String>();
	File dir = new File(directory);
	for (File file : dir.listFiles()) {
	    result.add(file.getName());
	}
	return result;
    }

    public SerializableImageProcessorResult getResult(String name) throws IOException {
	File input = new File(directory, name);
	StreamSource source = new StreamSource(new FileInputStream(input));
	return (SerializableImageProcessorResult) marshaller.unmarshal(source);
    }

    public String getDirectory() {
	return directory;
    }

    public void setDirectory(String directory) {
	this.directory = directory;
    }

    public Jaxb2Marshaller getMarshaller() {
	return marshaller;
    }

    public void setMarshaller(Jaxb2Marshaller marshaller) {
	this.marshaller = marshaller;
    }
    
}

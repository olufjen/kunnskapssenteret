package no.kunnskapssenteret.evs.cache.test;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import no.kunnskapssenteret.evs.cache.CachedContentReader;

public class TestGetContentByCategoryCached extends CachedContentReader implements Runnable {
	String args[] = null;
	String threadName = null;
	String key = "thekey";

	@Override
	public Document getStoredContent() {
		Document doc = null;
		//try {
			//Thread.sleep((long) Math.random() * 3000);
			long timeStart = System.currentTimeMillis();
			doc = fromFile();
			System.out.println("generated DOM in " + (System.currentTimeMillis() - timeStart) + " millis");
			//Thread.sleep((long) Math.random() * 3000);
			//} catch (InterruptedException ie) {
			//System.out.println(ie.getMessage());
			//}
		return doc;
	}

	@Override
	protected void initCacheParams() {
		setCacheScope("APPLICATION");
	}

	@Override
	protected void initGetContentParams() {
		// TODO Auto-generated method stub
	}
	
	public static void main(String args[]) throws Exception {
		for (int i = 0; i < 500; i++) {
			TestGetContentByCategoryCached test = new TestGetContentByCategoryCached();
			test.args = args;
			test.threadName = "thread no " + i;
			System.out.println(test.threadName + " starting");
			Thread.sleep(2);
			new Thread(test).start();
		}
	}
	
	public void run() {
		super.timeToLive = 7;
		for (int i = 0; i < 100; i++) {
			System.out.println(threadName + " leeching");
			try {
				Thread.sleep((long) (Math.random() + 1) * 5);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
			System.out.println(threadName + " accessing journal list..");
			lookupContent(key);
			System.out.println(threadName + " done accessing journal list");
		}
	}
	
	private Document fromFile() {
		Document doc = null;
		if ((args.length < 1) || (args.length > 3)) {
            System.out.println(
              "Usage: java SAXBuilderDemo " +
              "[XML document filename] ([expandEntities] [SAX Driver Class])");
            return null;
        }

        boolean expandEntities = true;

        // Load filename and SAX driver class
        String filename = args[0];
        String saxDriverClass = null;
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("false")) {
                expandEntities = false;
            }
            if (args.length > 2) {
                saxDriverClass = args[2];
            }
        }

        // Create an instance of the tester and test
        try {
            SAXBuilder builder = null;
            if (saxDriverClass == null) {
                builder = new SAXBuilder();
            } else {
                builder = new SAXBuilder(saxDriverClass);
            }
            //builder.setExpandEntities(expandEntities);
            //builder.setIgnoringBoundaryWhitespace(true);

            doc = builder.build(filename);

            //XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            //outputter.setExpandEmptyElements(true);
            //outputter.output(doc, System.out);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
	}
}
// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 09.11.2008 12:21:05
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 

package no.kunnskapssenteret.evs.cache;

import java.io.*;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public final class Utilities
{

    public Utilities()
    {
    }

    public static void prettyPrint(Document document)
        throws IOException
    {
        prettyPrint(document, ((OutputStream) (System.out)));
    }

    public static void prettyPrint(Document document, OutputStream outputstream)
        throws IOException
    {
        XMLOutputter xmloutputter = new XMLOutputter();
        xmloutputter.setFormat(Format.getPrettyFormat());
        xmloutputter.output(document, outputstream);
    }

    public static int copy(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        int i = 0;
        byte abyte0[] = new byte[1024];
        do
        {
            int j = inputstream.read(abyte0);
            if(j > 0)
            {
                outputstream.write(abyte0, 0, j);
                i += j;
            } else
            {
                return i;
            }
        } while(true);
    }

    public static Document parseXml(InputStream inputstream)
        throws JDOMException, IOException
    {
        SAXBuilder saxbuilder = new SAXBuilder();
        return saxbuilder.build(inputstream);
    }

    public static byte[] copyToBytes(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        copy(inputstream, bytearrayoutputstream);
        return bytearrayoutputstream.toByteArray();
    }

    public static String copyToString(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = copyToBytes(inputstream);
        return new String(abyte0, "UTF-8");
    }
}
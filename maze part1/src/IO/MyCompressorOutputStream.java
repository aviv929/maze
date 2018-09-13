package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ronnie on 13-May-17.
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream out)
    {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        if(b == null || b.length < 12)
            return;

        int counter = 1;
        int lastByte;
        int n;

        for(int i=0;i<12;i++)
        {
            n = b[i] & 0xff;
            out.write(n);
        }

        n = b[12];
        if(n == 1)
        {
            out.write(0);
            lastByte = 1;
        }
        else
            lastByte = 0;

        for(int i=13;i<b.length;i++)
        {
            n = b[i];
            if(n == lastByte)
            {
                if(counter == 255)
                {
                    out.write(counter);
                    counter = 0;
                    out.write(counter);
                }
                counter++;
            }
            else
            {
                out.write(counter);
                lastByte = n;
                counter = 1;
            }
            if(i == b.length-1)
                out.write(counter);
        }
    }
}
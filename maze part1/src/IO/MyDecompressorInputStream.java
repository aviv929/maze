package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronnie on 13-May-17.
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream in)
    {
        this.in = in;
    }

    @Override
    public int read() throws IOException
    {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException
    {
        if(b == null || b.length < 12)
            return -1;

        int index = 0;
        int next = 0;
        boolean zero = true;

        while(index < b.length && (next = in.read()) != -1)
        {
            if(index < 12)
            {
                b[index] = (byte)next;
                index++;
            }
            else
            {
                int t = next;
                while(t>0)
                {
                    if(zero)
                        b[index] = 0;
                    else
                        b[index] = 1;
                    t--;
                    index++;
                }
                zero = !zero;
            }
        }
        return 1;
    }


}

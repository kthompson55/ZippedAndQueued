import edu.neumont.io.Bits;

public class HuffmanCompressor<E extends Comparable<E>>
{	
	public byte[] compress(HuffmanTree<E> tree, byte[] b) // returns compressed byte value
	{
		Bits bits = new Bits();
		for(int i = 0; i < b.length; i++)
		{
			tree.fromByte(b[i], bits);
		}
		while(bits.size()%8 != 0)
		{
			bits.add(false);
		}
		System.out.println("Bits saved: " + (b.length - bits.size()/8));
		
		String binaryString = "";
		for(int i = 0; i < bits.size(); i++)
		{
			if(bits.get(i))
			{
				binaryString += "1";
			}
			else
			{
				binaryString += "0";
			}
		}
		System.out.println(binaryString);
		int byteSize = bits.size()/8;
		byte[] byteArray = new byte[byteSize];
		
		for(int i = 0; i < byteSize; i++)
		{
			int start = i*8;
			int end = i*8+8;
			byteArray[i] = (byte)Integer.parseInt((binaryString.substring(start,end)),2);
		}

		return byteArray;
	}
	
	public byte[] decompress(HuffmanTree<E> tree, int uncompressedLength, byte[] b) // returns uncompressed byte value from compressed byte
	{
		byte[] byteList = new byte[uncompressedLength];
		Bits bitHolder = new Bits();
		for(int i = 0; i < b.length; i++)
		{
			for(int j = 7; j > -1; j--)
			{
				bitHolder.add(((b[i]>>j) & 1) == 1);
			}
		}
		System.out.println("Uncompressed Length: " + uncompressedLength);
		System.out.println("Current Bit Length: " + bitHolder.size());
		for(int i = 0; i < uncompressedLength; i++)
		{
			// make sure the values being stored are capital-B Bytes
			byteList[i] = tree.toByte(bitHolder);
			System.out.print("[");
			for(int j = 0; j < byteList.length; j++) System.out.print(byteList[j]+",");
			System.out.println("]");
		}
		
		return byteList;
	}
}

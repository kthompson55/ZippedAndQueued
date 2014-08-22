import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Driver
{

	public static void main(String[] args) throws IOException
	{
		HuffmanCompressor<Node<Byte>> compressor = new HuffmanCompressor<Node<Byte>>();
		
		byte[] testBytes = {0,0,1,2,0,3};
		//byte[] testBytes = Files.readAllBytes(Paths.get("C:\\Users\\kthompson\\Pictures\\Hey.bmp"));
		//byte[] testBytes = Files.readAllBytes(Paths.get("C:\\Users\\kthompson\\Pictures\\Abstract.png"));
		//byte[] testBytes = Files.readAllBytes(Paths.get("C:\\Users\\kthompson\\Desktop\\Homework\\Algorithms\\compressed.huff"));	
		
		HuffmanTree<Node<Byte>> tree = new HuffmanTree<Node<Byte>>(testBytes);
		System.out.print("Initial: {");
		for(int i = 0; i < testBytes.length; i++)
		{
			System.out.print(testBytes[i] + ",");
		}
		System.out.println("}");
		System.out.println("Total Bytes: " + testBytes.length);
		
		byte[] compressed = compressor.compress(tree, testBytes);
		System.out.print("Compressed: {");
		for(int i = 0; i < compressed.length; i++)
		{
			System.out.print(compressed[i]+",");
		}
		System.out.println("}");
		
		byte[] uncompressed = compressor.decompress(tree, testBytes.length, compressed); // change depending on the situation
		System.out.print("Uncompressed: {");
		for(int i = 0; i < uncompressed.length; i++)
		{
			System.out.print(uncompressed[i]+",");
		}
		System.out.println("}");
		
		Files.write(Paths.get("C:\\Users\\kthompson\\Desktop\\Homework\\Algorithms\\Picture.png"),uncompressed);
		
//		byte[] testBytes = Files.readAllBytes(Paths.get("C:\\Users\\kthompson\\Desktop\\Homework\\Algorithms\\compressed.huff"));
//		long[] data = {423, 116, 145, 136, 130, 165, 179, 197, 148, 125, 954, 156, 143, 145, 164, 241, 107, 149, 176, 153, 121, 164, 144, 166, 100, 138, 157, 140, 119, 138, 178, 289, 360, 120, 961, 195, 139, 147, 129, 192, 119, 146, 138, 184, 137, 196, 163, 331, 115, 160, 127, 172, 176, 181, 149, 194, 138, 154, 163, 167, 196, 174, 250, 354, 142, 169, 170, 209, 205, 179, 147, 245, 108, 179, 148, 186, 131, 160, 112, 219, 118, 204, 164, 154, 154, 175, 189, 239, 126, 145, 185, 179, 149, 167, 152, 244, 189, 257, 234, 208, 179, 170, 171, 178, 184, 189, 203, 184, 204, 208, 187, 163, 335, 326, 206, 189, 210, 204, 230, 202, 415, 240, 275, 295, 375, 308, 401, 608, 2099, 495, 374, 160, 130, 331, 107, 181, 117, 133, 476, 129, 137, 106, 107, 237, 184, 143, 122, 143, 1596, 205, 121, 170, 123, 124, 150, 132, 143, 133, 178, 308, 96, 102, 114, 176, 159, 149, 123, 199, 1156, 119, 144, 237, 131, 155, 143, 225, 92, 125, 117, 138, 135, 154, 124, 137, 121, 143, 149, 141, 177, 159, 247, 384, 302, 120, 95, 140, 87, 1460, 155, 199, 111, 198, 147, 182, 91, 148, 119, 233, 445, 1288, 138, 133, 122, 170, 156, 257, 143, 149, 180, 174, 132, 151, 193, 347, 91, 119, 135, 182, 124, 152, 109, 175, 152, 159, 166, 224, 126, 169, 145, 220, 119, 148, 133, 158, 144, 185, 139, 168, 244, 145, 167, 167, 262, 214, 293, 402};
//		HuffmanTree tree = new HuffmanTree(data);
//		
//		byte[] uncompressed = compressor.decompress(tree, 54679, testBytes);
//		Files.write(Paths.get("C:\\Users\\kthompson\\Desktop\\Homework\\Algorithms\\Picture.png"),uncompressed);
	}
}

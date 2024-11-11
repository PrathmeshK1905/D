import java.util.*;

class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

class DAA_LA2 {
    // Builds the Huffman tree and generates encoding
    public static void encode(Map<Character, String> huffmanCodes, HuffmanNode root, String code) {
        if (root == null) return;
        if (root.left == null && root.right == null) huffmanCodes.put(root.character, code);
        encode(huffmanCodes, root.left, code + "0");
        encode(huffmanCodes, root.right, code + "1");
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encode: ");
        String text = scanner.nextLine();

        // Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

        // Build priority queue for Huffman Tree nodes
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));
        freqMap.forEach((ch, freq) -> pq.add(new HuffmanNode(ch, freq)));

        // Construct the Huffman Tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll(), right = pq.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left; newNode.right = right;
            pq.add(newNode);
        }
        HuffmanNode root = pq.poll();

        // Generate Huffman codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        encode(huffmanCodes, root, "");

        // Display results
        System.out.println("Huffman Codes:");
        huffmanCodes.forEach((ch, code) -> System.out.println(ch + ": " + code));
        System.out.print("Encoded Text: ");
        for (char ch : text.toCharArray()) System.out.print(huffmanCodes.get(ch));
    }
}

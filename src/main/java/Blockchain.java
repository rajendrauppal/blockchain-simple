
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    private static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("Hi, I'm the first block", "0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block(
                "Yo, I'm the second block",
                blockchain.get(blockchain.size() - 1).getHash())
        );
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block(
                "And, I'm the third block",
                blockchain.get(blockchain.size() - 1).getHash())
        );
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Blockchain is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);

        System.out.println("The blockchain: ");
        System.out.println(blockchainJson);

    }

    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        boolean result = true;
        // iterate through blockchain to check hashes
        for (int i = 1; i < blockchain.size(); ++i) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // compare registered hash and calculated hash
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                result = false;
            }

            // compare registered previous hash and calculated previous hash
            if (!previousBlock.getHash().equals(previousBlock.calculateHash())) {
                System.out.println("Previous hashes not equal");
                result = false;
            }

            // check if hash is solved
            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined!");
                result = false;
            }
        }

        return result;
    }

}

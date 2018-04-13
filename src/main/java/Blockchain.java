
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    private static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("Hi, I'm the first block", ""));

        blockchain.add(new Block(
                "Yo, I'm the second block",
                blockchain.get(blockchain.size() - 1).getHash())
        );

        blockchain.add(new Block(
                "And, I'm the third block",
                blockchain.get(blockchain.size() - 1).getHash())
        );

        String blockchainGson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);

        System.out.println(blockchainGson);

//        Block genesisBlock = new Block("hey, I'm the first block", "0");
//        System.out.println("Hash for block 1 : " + genesisBlock.getHash());
//
//        Block secondBlock = new Block("Yo im the second block", genesisBlock.getHash());
//        System.out.println("Hash for block 2 : " + secondBlock.getHash());
//
//        Block thirdBlock = new Block("Hey im the third block", secondBlock.getHash());
//        System.out.println("Hash for block 3 : " + thirdBlock.getHash());

    }

    public static boolean isChainValid() {
        Block currentBlock, previousBlock;

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
        }
        return result;
    }

}

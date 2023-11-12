package com.cat.tree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodesBytes));

        byte[] sourceBytes = decode(huffmanCodes,huffmanCodesBytes);
        System.out.println(new String(sourceBytes));
    }
    //将文件解压
    /**
     * @param zipFile 压缩文件的全路径
     * @param dstFile 解压缩文件放入的路径
     */
    public static void unZipFile(String zipFile, String dstFile){

        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件
            os.write(bytes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
     //将文件进行压缩
    /**
     * @param srcFile 传入压缩文件的全路径
     * @param dstFile 压缩文件放入的目录文件
     */
    public static void zipFile(String srcFile, String dstFile){
        
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try{
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);

            //这里我们以对象流的方式写入赫夫曼编码，是为了恢复源文件时使用
            //不写入压缩文件 无法恢复
            oos.writeObject(huffmanCodes);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes重新先转成赫夫曼编码对应的二进制的字符串
    //2.赫夫曼编码对应的二进制的字符串对照赫夫曼编码转成-> "i like like like java do you like a java"

    //完成对压缩数据的解码
    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){

        //1.先得到huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0;i < huffmanBytes.length;i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        System.out.println(stringBuilder.toString());
        //把字符串按照指定的赫夫曼编码表进行解码
        //把赫夫曼编码表进行调换 a = 100 -> 100 = a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        System.out.println(map);
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //遍历stringBuilder
        for(int i = 0;i < stringBuilder.length();){
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while(flag){
                //递增的取出key
                String key = stringBuilder.substring(i, i+count);//i不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null){
                    //没有匹配到
                    count++;
                }else{
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        System.out.println(list);
        //当for循环结束后，list中就存放了所有的字符"i like like like java do you like a java"
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for(int i = 0;i < b.length;i++){
            b[i] = list.get(i);
        }
        return b;
    }
    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的byte
     * @param flag 标识是否需要补高位，如果是true，需要，反之不补,最后一个字节，无需补高位
     * @return 是b对应的二进制的字符串（按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b){
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数我们还存在补高位
        if(flag){
            temp |= 256;//按位与 256  10000 0000 | 0000 0001 => 10000 0001
        }
        String str = Integer.toBinaryString(temp);//返回temp对应的二进制补码
        if(flag){
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }
    //将前面的方法进行封装，便于我们调用//将前面的方法进行封装，便于我们调用
    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<HfmNode> nodes = getNodes(bytes);
        //创建赫夫曼树
        HfmNode huffmanTreeRoot = createHuffmanTree(nodes);
        //根据赫夫曼树生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }
    private static byte[] zip(byte[] bytes,Map<Byte, String> huffmanCodes){
        //1.利用huffmanCodes将 bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将“10101100010111111110.....”转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个byte
        for(int i = 0; i < stringBuilder.length();i+=8) {//因为每8位对应一个byte，所以步长为8
            String strByte;
            if(i + 8 > stringBuilder.length()){//不够8位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i,i+8);
            }

            //将strByte 转成一个byte 放到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }
    //生成赫夫曼树对应的赫夫曼编码
    //1.将赫夫曼编码存放在Map<Byte, Sttring>形式
    //32->01 97->100 100->11000等形式
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //2.在生成赫夫曼编码时，需要拼接路径，定义一个StringBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    private static Map<Byte, String> getCodes(HfmNode root){
        if(root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径：左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(HfmNode node,String code,StringBuilder stringBuilder){
        //将code加入到stringBuilder2
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            //判断当前node是否为叶子结点
            if(node.data == null){//非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else{//说明是叶子结点
                //表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }
    private static void preOrder(HfmNode root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是List形式[Node[data = 97,weight = 5]],Node[data = 32,weight = 9]..........]
     */
    private static List<HfmNode> getNodes(byte[] bytes){
        //创建ArrayList
        ArrayList<HfmNode> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        Map<Byte,Integer> counts = new HashMap<>();
        for(byte b:bytes){
            Integer count = counts.get(b);
            if(count == null){//Map还没有字符数据，第一次
                counts.put(b,1);
            }else{
                counts.put(b,count + 1);
            }
        }
        //把每个键值对转成Node对象，并加入nodes集合
        //遍历map
        for(Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new HfmNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
    //通过List创建对应的赫夫曼树
    public static HfmNode createHuffmanTree(List<HfmNode> nodes) {
        while (nodes.size() > 1) {
            //排序从小到大
            Collections.sort(nodes);
            //取出根结点权值最小的两颗二叉树
            HfmNode leftNode = nodes.get(0);
            HfmNode rightNode = nodes.get(1);
            //构建一颗新的二叉树
            HfmNode parent = new HfmNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArraysList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入nodes
            nodes.add(parent);

        }
        //返回赫夫曼树的root结点
        return nodes.get(0);
    }
}
class HfmNode implements Comparable<HfmNode>{
    Byte data;//存放数据本身，比如‘a’ =>97
    int weight;//权重，表示字符出现的次数
    HfmNode left;
    HfmNode right;

    public HfmNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(HfmNode o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HfmNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
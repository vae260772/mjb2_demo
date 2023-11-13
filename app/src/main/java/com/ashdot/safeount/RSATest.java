package com.ashdot.safeount;


import java.security.PrivateKey;
import java.security.PublicKey;

public class RSATest {
    //base64 code https://www.toolnb.com/tools-lang-zh-TW/testrsa.html
    static String PUCLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAxFTLbuJnBS87F1h12VEQ\n" +
            "4oD5IXh4/zdqm/aVyozkAC9SYSNkcAAewxGFo99bFh/kYHsAGWUq8uFC1BryR1t/\n" +
            "v8pt3TXya98majG3J4xrnrZKb6P80Qgjf+nniXIwLaBguB58A0ytzm2hhBsPg3C0\n" +
            "WZqQCG2KxHsgarGG7iOks7Mu6wPHzlN2eTXG+NPVcVmitNTig+MiiUsB57EURIl+\n" +
            "4JL/L8qqlPXbzoZWbA3dN3mL2jfuf6qTMmQn+WW/eVepykCCtV2mCKg0OJ/yUf41\n" +
            "NSgIRXxQ8DYS+ODxDlNA7t1QsVgaJPQriiSQdY4dHfYLOL21QY+8PMpC22Pxl8uB\n" +
            "fxNTQlhX54DmSPXUnhT1BDznydZGIO1sqBXahbZQf9sZe4M1N7s2mKRBu2UrI+O5\n" +
            "j1Ae0dfL2aW458oI4lXWZsU2/kbcZCLdn7z+UM1BC3GclQd1f68T0td59Gwom8Qz\n" +
            "9mO2IseHdF+Yd3/peX6WKQ+aFuFAmNOfS+S870XeBBh7bbty8YbRN0AU1W+49cjE\n" +
            "7ZdEltH5OS+3r+GxKYcoubj9T3LqK/4nSQbZJYhfrQ6p46Z9oBBpoHfHA9NBWdG0\n" +
            "luhY3dkBoz8tRJQeR0n81LtmItkAyOKQIV5ejimifiLPcTCo38gx1EzTayXdoVHW\n" +
            "YE3+E6rYZPWs4Te/sWxhgGMCAwEAAQ==";
    static String PRIVATE_KEY = "MIIJQgIBADANBgkqhkiG9w0BAQEFAASCCSwwggkoAgEAAoICAQDEVMtu4mcFLzsX\n" +
            "WHXZURDigPkheHj/N2qb9pXKjOQAL1JhI2RwAB7DEYWj31sWH+RgewAZZSry4ULU\n" +
            "GvJHW3+/ym3dNfJr3yZqMbcnjGuetkpvo/zRCCN/6eeJcjAtoGC4HnwDTK3ObaGE\n" +
            "Gw+DcLRZmpAIbYrEeyBqsYbuI6Szsy7rA8fOU3Z5Ncb409VxWaK01OKD4yKJSwHn\n" +
            "sRREiX7gkv8vyqqU9dvOhlZsDd03eYvaN+5/qpMyZCf5Zb95V6nKQIK1XaYIqDQ4\n" +
            "n/JR/jU1KAhFfFDwNhL44PEOU0Du3VCxWBok9CuKJJB1jh0d9gs4vbVBj7w8ykLb\n" +
            "Y/GXy4F/E1NCWFfngOZI9dSeFPUEPOfJ1kYg7WyoFdqFtlB/2xl7gzU3uzaYpEG7\n" +
            "ZSsj47mPUB7R18vZpbjnygjiVdZmxTb+RtxkIt2fvP5QzUELcZyVB3V/rxPS13n0\n" +
            "bCibxDP2Y7Yix4d0X5h3f+l5fpYpD5oW4UCY059L5LzvRd4EGHttu3LxhtE3QBTV\n" +
            "b7j1yMTtl0SW0fk5L7ev4bEphyi5uP1Pcuor/idJBtkliF+tDqnjpn2gEGmgd8cD\n" +
            "00FZ0bSW6Fjd2QGjPy1ElB5HSfzUu2Yi2QDI4pAhXl6OKaJ+Is9xMKjfyDHUTNNr\n" +
            "Jd2hUdZgTf4Tqthk9azhN7+xbGGAYwIDAQABAoICAQC/bV4dc6q3J2IaVSozBhPM\n" +
            "haxjsi9nyQStpTRQFieI9psAE54uhi94S3FeYr1RSS9H0yY5nKbPwkuCu/NXGCPr\n" +
            "QNj2Q09nVmECUg8fBI86ZMsXJ3Gt7p2ObuZO4YOQOTuUykRogy9Egyhkwm3R3uY6\n" +
            "J7YgQY54HQVygj3pm99PR3qLs1fyGPb/ICwtGX6X0qoOXzOtHJ3j97qXydTVLqsD\n" +
            "FNC0LIs+SqpTKDDDkpELMfvmfXqSCCMVkwpW5ZpYGd13Uocfh3wIHNkuDOYchBwl\n" +
            "BC9scNYKIO9GsyzWpt49CJVRFXIIbfq16wjfVf2V5Pz0CiTdE+SZ8SxW9Y/uTYKo\n" +
            "SzBUFv2+xkqb22/CK1kzv8v8OF/TDd/5l7uiWQU9xAWvJkmjTC2gzXpYI/F5omKA\n" +
            "Y1+w7sgVTr4Pt6kR8bCWnxGq6VlzyM9UjG3x+frOYtsOlWkOsVtE3P6C/r2dQDBc\n" +
            "UfGgm1H4vHdKRYEsa/T19dSxxW98wIs/ZsZnrnY+geVzmrP7GYp/UCcl2QISZdIP\n" +
            "/d6tvP+Z2QI5vxaRnFH5yRPJFY5eC0qATk0MmHa5P/vZNoZUvApH+EO89sGyNc7d\n" +
            "lazpmKwc5o4kb7tRbmW21tg6G/Pu1NtIIigfPq505OSACZbENiaXs29Pc1yxNLRN\n" +
            "TxxLaC+zv4SStiePSOmU4QKCAQEA5xtlAY6Qhm6+uFotWxt8vwl06eMJmzWUnwNG\n" +
            "ejHVLF7bT/bIMLbPTDvpeBKsuQQirYhV4gX4SJyz7TCcqVdQJ/NICXXxaZGjyMp/\n" +
            "SrSBl8rjSZfSMAW2RTReh852XvtcNqORT2orfCNcrRLc4jlTtanB7H0IvXXKTv4I\n" +
            "KtUbc7FFpFzf/pKJ6bX8NO7ED5u8lkCVZ1KYmJQW/RFhw9aSESI4ChO4QyOHDVxy\n" +
            "ZnuS8EVLkBFmDqR6YXWFGePVkbNo61VGj7GI/Z5FZxeYaWekSaguArN+UOS0RI0u\n" +
            "vpmIt+2RS8+9VB3hHAt3L/249N1DcqUeXmLqfulanfQy2GvqnwKCAQEA2Xp7zB1N\n" +
            "y7+YkwmaApLpGJ+T3kjsq/WRW6xnW+IQnN0AuBabbSM40U274e+H/mIE2XUOBLRI\n" +
            "03lACOkXjwhC7MeS8MyOHhkNGgcNAt/VzYnEpjr9uViqzv5CE3abSM1gbbnLmq+k\n" +
            "JC62OzITMQmTXXQjr8Vlnr5Y0z4ZhFPeuUD56+Jlg2o2tZ0Mu8aTMCEC6o3KVv+y\n" +
            "9/pixXAqyUB4ibC+oBw6CKNFxvyds2rJHgk26xOs2U+SsdevC6zeZJHGqB6QY6a2\n" +
            "K0WGqPsTiinEuMTLloOa4iOP2nhIrvE6Lv29B/a93E9AT5nmxvPnlqAJmNpqFlRz\n" +
            "ds+A6r6KWr8XvQKCAQB+jdHR+G/MLfjimv8bVQIK2vLa4SjxhXXNXc3KPF+v3IHc\n" +
            "WBdoFbOO24AN8KwE5O7YeEAPdZVwrd8Zk/U2BkoullpsU9nPJaG64qiyEAanSMMO\n" +
            "GcVP5tDZNMRPOhA4Ew18HjYEdu85G2Q0oo+ykhH0+PDov895cHdIqnNugSR3RiwM\n" +
            "xY8jqLs88BscIWPb6DxtSxCFdAV8CBxOmKYgx+9U0pg9bG8K1Pmvp2A+Ho1oZYnr\n" +
            "apuoiQk7NU+dzdW147kgpaHuLhoGSBVZTuJ3VfnxG9eWQxR418SfhXCDjy//CGj4\n" +
            "StoypeoPoXezIA2YiVHWBfWLWj9vH/ovZPoy6WWhAoIBABeH5CsypH8+LNEEAfDz\n" +
            "Wls6sPIlK85zVJX5snlDnY6Lbw98CrclT3l+Eq0gkLNagbHRs4GYzCRtgxsztUPZ\n" +
            "nfwmn+SEDYB+L2e4iZTdCTIjDO77hQZv7JNW+WlHtQeMiTl2F+yVwonTsA35ng/5\n" +
            "PEOpVE8paZRifhPlLTefJ4Md36zBI0Cz1lWSN5V9p+FWQQ2F5/W/1upz4goA1/BM\n" +
            "lH45bHbAdxsGJ3+fR6TpEfe5AYMv7szYikPJ1XvK6YkyKK96Fx9hI2bNMAPKntOd\n" +
            "L1banDid5r0/Icp+p0aefTAmbjSVYveab5ftTAYMz+sBR+iNAVuHKbMaQpWjpi6p\n" +
            "bHUCggEAIVM5JxFBONIoX13Scyg+UnhmMjmXOgsSITmZnFGsH6mVXfmwv6lKdAZv\n" +
            "VG7AqESdOnkO9qix1a6+IIrILiWn20MkuCKkps/s8y4TMCydhvArBFmsvGTr7ANK\n" +
            "zjl0ixW6gdQ0WiQgs45n7VFgM+3dceB3hG/UekSF0Pq8IRWzUb2KwxNOXKH0wcWn\n" +
            "X4YmgGZDxry2rBuyL/tB2UcsX6Eg3DlooiaNTuZDnkL5pVX5eYiGRtc09GLxkOfp\n" +
            "DIoANO0+6RZ5TFKmq/InRMT0D/E0R+t/zsa41ECA7fA97GLhSS2F2BCvFo4S5xiv\n" +
            "INOEARWIIzsUvgHcSmBswA54eF/KQw==";

    public static void main(String[] args) throws Exception {
        String source = "zxp";
        //   InputStream publicIS = new FileInputStream("C:\\rsa_public_key.pem");
        //  InputStream privateIS = new FileInputStream("C:\\pkcs8_rsa_private_key.pem");
        PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
        ///  PublicKey publicKey = RSAUtils.loadPublicKey(publicIS);
        PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
        ///////   PrivateKey privateKey = RSAUtils.loadPrivateKey(privateIS);
        byte[] b1 = RSAUtils.encryptData(source.getBytes(), publicKey);
        System.out.println(">>>" + new String(RSAUtils.decryptData(b1, privateKey)));
    }
}
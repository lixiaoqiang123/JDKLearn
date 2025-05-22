package sm4;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

public class Sm4Util {

    @Test
    public void testSm4(){
        String sm4Key = "biay9t2rsft8rm5n";
        String text = "pUmLs6y3604e35597b04ca2a3fa52d943e7c109fd02da7c5+35312145";
        SymmetricCrypto symmetricCrypto = SmUtil.sm4(sm4Key.getBytes());
        System.out.println(symmetricCrypto.encryptHex(text));
    }
}

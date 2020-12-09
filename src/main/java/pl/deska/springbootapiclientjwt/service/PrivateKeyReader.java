package pl.deska.springbootapiclientjwt.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;

public  class PrivateKeyReader {

    static{
        Security.addProvider(new BouncyCastleProvider());
    }

    public  RSAPrivateKey get(String keysFilePath) throws Exception {
        File keysFile = new File(keysFilePath);

        PEMParser pemParser = new PEMParser(new FileReader(keysFile));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
        KeyPair keyPair = converter.getKeyPair(pemKeyPair);

        return (RSAPrivateKey) keyPair.getPrivate();
    }

}

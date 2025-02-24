package com.luoyi.example.jd.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListTest {

    public static void main(String[] args) {

//        Object o = null;
//        List<String> list = new ArrayList<>();
//        System.out.println(ObjectUtil.isNotNull(o));
//        System.out.println(ObjectUtil.isNotNull(list));


        String privateKey = "MIIEpAIBAAKCAQEAt5aBpRruyVnBONkzuS9RBzK8Kp13MdSOT6jkpO7NLElya9Vm/kvYVO8bPILwdLkWP1iFPbmvCZzg2thxIw7WsufioXNRMeDm+6Y5uR5olMD0paajs68O4MU2PlX5a/ZB+ZbglsbDgqAS+mZJnWYWVtGmxS3tz/Ftkg4G7EnxVghIMagIxZDhujOR42NFX/9O5r0P0t+I77gDUwvAOa1bEz4O7AmjtDYFKg1ifztYspm9flB/pENJuZPQnUt9J5LWopj+nnWefh6IFwivUAvni+wAFfmJ0n3t6a+izxI6lnbAekVVtKLwGOJ3ShT1qJcBy7PNqwmbYVZD0x8jNDDBXQIDAQABAoIBAGhUDGrCKm6cJfTjYcRcAkT/7PSetYzIyPK1/PFJj6kaN/7VsAjVGyPxGV5C3tjGEcF/0YApVDI3R4S2OVz94r6ajizHtp2MvFbHuMfdYvsdyCmW0DKdSewrxk6YVvymXgmzVv/4BkTmKMLJfA4JZKqDy60EE7/26Bh3BjIjNxV2+hsznhyuIcTI8+vnn8Bbx+Sp0aU4apSFusqne2C8IlSeP4ZwBb9vqGajw4JIvCk055bsp1EHRGlcoOKEBsDx55Lsi0fOH8eyNGIma9zJu1ZtmHcmvWPDjdhKNnm2yEUPdBTtrqQWARlsCeD2QrNAOqeBYa2hTUOiYCvgJWz6bWECgYEA7CW46cbmgZCEUD1VtDc5Z4gNc2cKQ3pgu2Da14XZrjPicvagGkqHmW8CNFDr2ymSYch7kRBObS+pRMCtOe+peUVfErCNxfL8QEqf9cQWRw1hpGO7Vk3G7DhQQ+R5UZPe6KFLyn3eOUuaVXzOsuQZ7nOgQ0nCZ45Q4IIRgO6/f2kCgYEAxwWedIXrS5xeWqgK6oZ8iAjQWwYbkFzAVnCxsiFLiisjIh+GK/Coqyjn9UlNWX1WBCkBzyV4ZR+JkcWx3V6wW184BeVi4eZiJOed6Dfck/nexNUXczbXnlVhYurdFZu3Gqax2nFSWNBW8ygKrKQJuHQ137C5sVjEPVnc/jNE59UCgYEAw88Lox0AKVLxw674D7Ty7nwL2zMHeahR6U2IaMleFGqFTJpfay19fqZjh4nJ6DJyZI5el4yT20l368BwYufs+Nei29Z12DuBGrZGKP1tE4FvA2aHAir/1QNk2sbqKdCZNv/33nG8AHpGALi6k+876VMLAoB4qHGDcoWniedt9nECgYBlczDNlwJVHZVfQu+lkv5h3ZRQAfIFCbKlnXaHJb+647haCZW8SkXAUe7DsyTxrBkvr7OTYzdW3NekbhD3le/x87y8DKz2GBkJVOPCXs9awvFShokYmukYdc9Zu/0Mqb6RoE1i24ctbK4V7uBaFpg55UsdBpnxlB0cZPVowruqLQKBgQC8D1UV6OcstoLB+r1sFxMkpK0fA/chAm9MQ/hk9bg6tbOBK9ecMj3vHOY+rdo1JAcJGFTf8fDHPb12Fp1SMaL3cw+QyGUHa4WqX5bHDDty4jpfGcVS2+LATIPSLOFyCz9jLgWJTvssqbFPbBiDFNzfr/QHCaSxl6bGfV7P72xENw==";
        String data = "{\"amount\":1049,\"businessType\":2,\"callBackUrl\":\"http://192.168.6.166/platform/order/open/order/v2/pay/call\",\"expirationTime\":1735806734,\"merchantNo\":\"510990120600365\",\"orderNumber\":\"PMO2025010210150793\",\"productSetCodes\":[{\"amount\":1049,\"productList\":[{\"img\":\"https://zjresource.oss-cn-shenzhen.aliyuncs.com/chain/d62040cfa9944ec8824d64768508b9a4.png\",\"num\":1,\"productName\":\"营养快线\",\"sku\":\"521301815666778987307008\"}],\"productSetCode\":\"C10002944\",\"productSetName\":\"百威单品选品集\"}],\"serialNumber\":\"SC_2025010216021410000067859\",\"terminalNo\":\"00000363\",\"userId\":106674046}";


        OrderDTO c = new OrderDTO();
        c.setAmount(1049L);
//        c.setBusinessType(2);
        c.setCallBackUrl("http://192.168.6.166/platform/order/open/order/v2/pay/call");
        c.setExpirationTime(1735806734);
        c.setMerchantNo("510990120600365");
        c.setOrderNumber("PMO2025010210150793");

        List<ProductSetCodeDTO> pList = new ArrayList<>();
        ProductSetCodeDTO p = new ProductSetCodeDTO();
        p.setAmount(1049L);
        List<ProductDTO> pList1 = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setImg("https://zjresource.oss-cn-shenzhen.aliyuncs.com/chain/d62040cfa9944ec8824d64768508b9a4.png");
        productDTO.setNum(1);
        productDTO.setProductName("营养快线");
        productDTO.setSku("521301815666778987307008");
        pList1.add(productDTO);
        p.setProductList(pList1);
        p.setProductSetCode("C10002944");
        p.setProductSetName("百威单品选品集");
        pList.add(p);
        c.setProductSetCodes(pList);
        c.setSerialNumber("SC_2025010216021410000067859");
        c.setTerminalNo("00000363");
        c.setUserId(106674046);

        String test = SignUtil.signRsaWithBean(c, privateKey);

    }
}

// This file is auto-generated, don't edit it. Thanks.
package com.xsg.health.aliyun;

import com.alibaba.fastjson2.JSON;
import com.aliyun.ocr_api20210707.Client;
import com.aliyun.ocr_api20210707.models.RecognizeBasicResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.xsg.health.dto.HealthMetrics;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class AliyunService implements InitializingBean {

    @Value("${aliyun.orc.accessKeyId}")
    String accessKeyId;

    @Value("${aliyun.orc.accessKeySecret}")
    String accessKeySecret;
    com.aliyun.ocr_api20210707.Client client;

    @Override
    public void afterPropertiesSet() {
        client = AliyunService.createClient(accessKeyId, accessKeySecret);
    }

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     */
    public static com.aliyun.ocr_api20210707.Client createClient(String accessKeyId, String accessKeySecret) {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HealthMetrics recognition(InputStream in) {
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.ocr_api20210707.models.RecognizeBasicRequest recognizeBasicRequest = new com.aliyun.ocr_api20210707.models.RecognizeBasicRequest()
                .setBody(in);
        try {
            // 复制代码运行请自行打印 API 的返回值
            RecognizeBasicResponse recognizeBasicResponse = client.recognizeBasicWithOptions(recognizeBasicRequest, new RuntimeOptions());

            System.out.println(recognizeBasicResponse);
            if (recognizeBasicResponse.getStatusCode() == HttpStatus.OK.value()) {
                OcrResultDTO ocrResultDTO = JSON.parseObject(recognizeBasicResponse.getBody().getData(), OcrResultDTO.class);
                return toHealthMetrics(ocrResultDTO);
            }
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        throw new RuntimeException("OCR接口异常");
    }

    public static HealthMetrics toHealthMetrics(OcrResultDTO ocrResultDTO) {
        List<PrismWordsInfoDTO> prism_wordsInfo = ocrResultDTO.getPrism_wordsInfo();
        HealthMetrics healthMetrics = new HealthMetrics();
        healthMetrics.setWeight(prism_wordsInfo.get(5).getWord());
        healthMetrics.setBmi(prism_wordsInfo.get(13).getWord());
        healthMetrics.setBodyFat(prism_wordsInfo.get(10).getWord());

        return healthMetrics;
    }
}

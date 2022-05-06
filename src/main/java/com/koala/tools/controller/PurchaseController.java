package com.koala.tools.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.purchase.PurchaseInfoModel;
import com.koala.tools.models.purchase.PurchaseModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/5/6 12:12
 * @description
 */
@RestController
@RequestMapping("tools/purchase")
public class PurchaseController {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @PostMapping("formatExcel")
    public Object formatExcel(@RequestBody String body) {
        PurchaseModel data = GsonUtil.toBean(body, PurchaseModel.class);
        ArrayList<PurchaseInfoModel> infoData = new ArrayList<>();
        Arrays.stream(data.getData().split("\n")).forEach(item -> {
            try {
                String itemData = item;
                Optional<String> start = Arrays.stream(itemData.split(" ")).findFirst();
                if (start.isPresent()) {
                    itemData = itemData.replaceFirst(start.get() + " ", "");
                    itemData = itemData.replaceFirst("531-", "");
                    String roomData = PatternUtil.matchData("([0-9]\\d*-[0-9]\\d*)(.*)", itemData);
                    String department = "";
                    String room = "";
                    if (!Objects.isNull(roomData) && roomData.contains("-")) {
                        String[] roomDataArray = roomData.split("-");
                        department = roomDataArray[0];
                        room = roomDataArray[1];
                    }
                    if (Objects.isNull(roomData)) {
                        return;
                    }
                    itemData = itemData.replaceFirst(roomData, "");
                    String mobile = PatternUtil.getPhoneNum(itemData);
                    itemData = itemData.replaceFirst(mobile, "");
                    itemData = itemData.replace(",", "").replace("，", "").replace("。", "");
                    PurchaseInfoModel info = new PurchaseInfoModel(
                            Integer.parseInt(department),
                            room,
                            itemData.trim(),
                            mobile
                    );
                    infoData.add(info);
                    logger.info(GsonUtil.toString(info));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info(item);
        });
        infoData.sort((o1, o2) -> {
            return Integer.compare(o1.getDepartment().compareTo(o2.getDepartment()), 0);
        });
        EasyExcelFactory.write("/Users/koala/Documents/demo.xls", PurchaseInfoModel.class).sheet("数据").doWrite(infoData);
        return "ok";
    }

}

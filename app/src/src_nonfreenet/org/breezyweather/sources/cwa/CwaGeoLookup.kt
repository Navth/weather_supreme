package org.breezyweather.sources.cwa

import com.google.maps.android.model.LatLng

// Temperature normals are only available at 27 stations (out of 700+).
// They are not available from the main weather API call,
// and must be called with a different endpoint with the exact station ID.
// This list allows matching a location to the nearest of those 27 stations.
//
// Information is updated once every 10 years. Last update was after 2020.
//
// Source (last checked 2024-12-16):
// https://opendata.cwa.gov.tw/dataset/climate/C-B0027-001
val CWA_NORMALS_STATIONS = mapOf(
    "466880" to LatLng(24.997646, 121.44202), // æ¿æ©‹ BANQIAO
    "466900" to LatLng(25.164888, 121.448906), // æ·¡æ°´ TAMSUI
    "466910" to LatLng(25.182587, 121.52973), // éžéƒ¨ ANBU
    "466920" to LatLng(25.037659, 121.514854), // è‡ºåŒ— TAIPEI
    "466930" to LatLng(25.162079, 121.54455), // ç«¹å­æ¹– ZHUZIHU
    "466940" to LatLng(25.133314, 121.74048), // åŸºéš† KEELUNG
    "466950" to LatLng(25.627975, 122.07974), // å½­ä½³å¶¼ PENGJIAYU
    "466990" to LatLng(23.975128, 121.61327), // èŠ±è“® HUALIEN
    "467060" to LatLng(24.596737, 121.85737), // è˜‡æ¾³ SU-AO
    "467080" to LatLng(24.763975, 121.75653), // å®œè˜­ YILAN
    "467300" to LatLng(23.25695, 119.667465), // æ±å‰å³¶ DONGJIDAO
    "467350" to LatLng(23.565502, 119.563095), // æ¾Žæ¹– PENGHU
    "467410" to LatLng(22.993238, 120.20477), // è‡ºå— TAINAN
    "467420" to LatLng(23.038385, 120.2367), // æ°¸åº· YONGKANG
    "467440" to LatLng(22.565992, 120.315735), // é«˜é›„ KAOHSIUNG
    "467480" to LatLng(23.495926, 120.43291), // å˜‰ç¾© CHIAYI
    "467490" to LatLng(24.145737, 120.684074), // è‡ºä¸­ TAICHUNG
    "467530" to LatLng(23.508207, 120.81324), // é˜¿é‡Œå±± ALISHAN
    "467540" to LatLng(22.355675, 120.903786), // å¤§æ­¦ DAWU
    "467550" to LatLng(23.487614, 120.95952), // çŽ‰å±± YUSHAN
    "467571" to LatLng(24.827852, 121.01422), // æ–°ç«¹ HSINCHU
    "467590" to LatLng(22.003897, 120.74634), // æ†æ˜¥ HENGCHUN
    "467610" to LatLng(23.097486, 121.37343), // æˆåŠŸ CHENGGONG
    "467620" to LatLng(22.036968, 121.55834), // è˜­å¶¼ LANYU
    "467650" to LatLng(23.881325, 120.90805), // æ—¥æœˆæ½­ SUN MOON LAKE
    "467660" to LatLng(22.75221, 121.15459), // è‡ºæ± TAITUNG
    "467770" to LatLng(24.256002, 120.523384) // æ¢§æ£² WUQI
)

// CWA issues warnings for different counties and specific townships classified as:
//  â€¢ å±±å€ Mountain ("M"): 59 townships
//  â€¢ åŸºéš†åŒ—æµ·å²¸ Keelung North Coast ("K"): 15 townships
//  â€¢ æ†æ˜¥åŠå³¶ Hengchun Peninsula ("H"): 6 townships
//  â€¢ è˜­å¶¼ç¶ å³¶ Lanyu and Ludao ("L"): 2 townships
//
// Source of township specification (last checked 2024-25-29):
// https://www.cwa.gov.tw/Data/js/info/Info_Town.js
//
// (Township codes in this list have been normalized
//  to match results from the reverse geocoding call.)
val CWA_TOWNSHIP_WARNING_AREAS = mapOf<String, String>(
    "10002110" to "M", // å®œè˜­ç¸£å¤§åŒé„‰ Datong Township, Yilan County
    "10002120" to "M", // å®œè˜­ç¸£å—æ¾³é„‰ Nanâ€™ao Township, Yilan County
    "10004080" to "M", // æ–°ç«¹ç¸£æ©«å±±é„‰ Hengshan Township, Hsinchu County
    "10004090" to "M", // æ–°ç«¹ç¸£åŒ—åŸ”é„‰ Beipu Township, Hsinchu County
    "10004120" to "M", // æ–°ç«¹ç¸£å°–çŸ³é„‰ Jianshi Township, Hsinchu County
    "10004130" to "M", // æ–°ç«¹ç¸£äº”å³°é„‰ Wufeng Township, Hsinchu County
    "10005070" to "M", // è‹—æ —ç¸£å“è˜­éŽ® Zhuolan Township, Miaoli County
    "10005080" to "M", // è‹—æ —ç¸£å¤§æ¹–é„‰ Dahu Township, Miaoli County
    "10005110" to "M", // è‹—æ —ç¸£å—åº„é„‰ Nanzhuang Township, Miaoli County
    "10005170" to "M", // è‹—æ —ç¸£ç…æ½­é„‰ Shitan Township, Miaoli County
    "10005180" to "M", // è‹—æ —ç¸£æ³°å®‰é„‰ Taiâ€™an Township, Miaoli County
    "10008020" to "M", // å—æŠ•ç¸£åŸ”é‡ŒéŽ® Puli Township, Nantou County
    "10008040" to "M", // å—æŠ•ç¸£ç«¹å±±éŽ® Zhushan Township, Nantou County
    "10008070" to "M", // å—æŠ•ç¸£é¹¿è°·é„‰ Lugu Township, Nantou County
    "10008090" to "M", // å—æŠ•ç¸£é­šæ± é„‰ Yuchi Township, Nantou County
    "10008100" to "M", // å—æŠ•ç¸£åœ‹å§“é„‰ Guoxing Township, Nantou County
    "10008110" to "M", // å—æŠ•ç¸£æ°´é‡Œé„‰ Shuili Township, Nantou County
    "10008120" to "M", // å—æŠ•ç¸£ä¿¡ç¾©é„‰ Xinyi Township, Nantou County
    "10008130" to "M", // å—æŠ•ç¸£ä»æ„›é„‰ Renâ€™ai Township, Nantou County
    "10009070" to "M", // é›²æž—ç¸£å¤å‘é„‰ Gukeng Township, Yunlin County
    "10010140" to "M", // å˜‰ç¾©ç¸£ç«¹å´Žé„‰ Zhuqi Township, Chiayi County
    "10010150" to "M", // å˜‰ç¾©ç¸£æ¢…å±±é„‰ Meishan Township, Chiayi County
    "10010160" to "M", // å˜‰ç¾©ç¸£ç•ªè·¯é„‰ Fanlu Township, Chiayi County
    "10010170" to "M", // å˜‰ç¾©ç¸£å¤§åŸ”é„‰ Dapu Township, Chiayi County
    "10010180" to "M", // å˜‰ç¾©ç¸£é˜¿é‡Œå±±é„‰ Alishan Township, Chiayi County
    "10013040" to "H", // å±æ±ç¸£æ†æ˜¥éŽ® Hengchun Township, Pingtung County
    "10013230" to "H", // å±æ±ç¸£è»ŠåŸŽé„‰ Checheng Township, Pingtung County
    "10013240" to "H", // å±æ±ç¸£æ»¿å·žé„‰ Manzhou Township, Pingtung County
    "10013250" to "H", // å±æ±ç¸£æž‹å±±é„‰ Fangshan Township, Pingtung County
    "10013260" to "M", // å±æ±ç¸£ä¸‰åœ°é–€é„‰ Sandimen Township, Pingtung County
    "10013270" to "M", // å±æ±ç¸£éœ§è‡ºé„‰ Wutai Township, Pingtung County
    "10013280" to "M", // å±æ±ç¸£ç‘ªå®¶é„‰ Majia Township, Pingtung County
    "10013290" to "M", // å±æ±ç¸£æ³°æ­¦é„‰ Taiwu Township, Pingtung County
    "10013300" to "M", // å±æ±ç¸£ä¾†ç¾©é„‰ Laiyi Township, Pingtung County
    "10013310" to "M", // å±æ±ç¸£æ˜¥æ—¥é„‰ Chunri Township, Pingtung County
    "10013320" to "H", // å±æ±ç¸£ç…å­é„‰ Shizi Township, Pingtung County
    "10013330" to "H", // å±æ±ç¸£ç‰¡ä¸¹é„‰ Mudan Township, Pingtung County
    "10014040" to "M", // è‡ºæ±ç¸£å‘å—é„‰ Beinan Township, Taitung County
    "10014110" to "L", // è‡ºæ±ç¸£ç¶ å³¶é„‰ Ludao Township, Taitung County
    "10014120" to "M", // è‡ºæ±ç¸£æµ·ç«¯é„‰ Haiduan Township, Taitung County
    "10014130" to "M", // è‡ºæ±ç¸£å»¶å¹³é„‰ Yanping Township, Taitung County
    "10014140" to "M", // è‡ºæ±ç¸£é‡‘å³°é„‰ Jinfeng Township, Taitung County
    "10014150" to "M", // è‡ºæ±ç¸£é”ä»é„‰ Daren Township, Taitung County
    "10014160" to "L", // è‡ºæ±ç¸£è˜­å¶¼é„‰ Lanyu Township, Taitung County
    "10015110" to "M", // èŠ±è“®ç¸£ç§€æž—é„‰ Xiulin Township, Hualien County
    "10015120" to "M", // èŠ±è“®ç¸£è¬æ¦®é„‰ Wanrong Township, Hualien County
    "10015130" to "M", // èŠ±è“®ç¸£å“æºªé„‰ Zhuoxi Township, Hualien County
    "10017010" to "K", // åŸºéš†å¸‚ä¸­æ­£å€ Zhongzheng District, Keelung City
    "10017020" to "K", // åŸºéš†å¸‚ä¸ƒå µå€ Qidu District, Keelung City
    "10017030" to "K", // åŸºéš†å¸‚æš–æš–å€ Nuannuan District, Keelung City
    "10017040" to "K", // åŸºéš†å¸‚ä»æ„›å€ Renâ€™ai District, Keelung City
    "10017050" to "K", // åŸºéš†å¸‚ä¸­å±±å€ Zhongshan District, Keelung City
    "10017060" to "K", // åŸºéš†å¸‚å®‰æ¨‚å€ Anle District, Keelung City
    "10017070" to "K", // åŸºéš†å¸‚ä¿¡ç¾©å€ Xinyi District, Keelung City
    "63000110" to "M", // è‡ºåŒ—å¸‚å£«æž—å€ Shilin District, Taipei City
    "63000120" to "M", // è‡ºåŒ—å¸‚åŒ—æŠ•å€ Beitou District, Taipei City
    "64000320" to "M", // é«˜é›„å¸‚å…­é¾œå€ Liugui District, Kaohsiung City
    "64000330" to "M", // é«˜é›„å¸‚ç”²ä»™å€ Jiaxian District, Kaohsiung City
    "64000360" to "M", // é«˜é›„å¸‚èŒ‚æž—å€ Maolin District, Kaohsiung City
    "64000370" to "M", // é«˜é›„å¸‚æ¡ƒæºå€ Taoyuan District, Kaohsiung City
    "64000380" to "M", // é«˜é›„å¸‚é‚£ç‘ªå¤å€ Namaxia District, Kaohsiung City
    "65000090" to "M", // æ–°åŒ—å¸‚ä¸‰å³½å€ Sanxia District, New Taipei City
    "65000100" to "K", // æ–°åŒ—å¸‚æ·¡æ°´å€ Tamsui District, New Taipei City
    "65000120" to "K", // æ–°åŒ—å¸‚ç‘žèŠ³å€ Ruifang District, New Taipei City
    "65000190" to "M", // æ–°åŒ—å¸‚çŸ³ç¢‡å€ Shiding District, New Taipei City
    "65000200" to "M", // æ–°åŒ—å¸‚åªæž—å€ Pinglin District, New Taipei City
    "65000210" to "K", // æ–°åŒ—å¸‚ä¸‰èŠå€ Sanzhi District, New Taipei City
    "65000220" to "K", // æ–°åŒ—å¸‚çŸ³é–€å€ Shimen District, New Taipei City
    "65000240" to "M", // æ–°åŒ—å¸‚å¹³æºªå€ Pingxi District, New Taipei City
    "65000250" to "K", // æ–°åŒ—å¸‚é›™æºªå€ Shuangxi District, New Taipei City
    "65000260" to "K", // æ–°åŒ—å¸‚è²¢å¯®å€ Gongliao District, New Taipei City
    "65000270" to "K", // æ–°åŒ—å¸‚é‡‘å±±å€ Jinshan District, New Taipei City
    "65000280" to "K", // æ–°åŒ—å¸‚è¬é‡Œå€ Wanli District, New Taipei City
    "65000290" to "M", // æ–°åŒ—å¸‚çƒä¾†å€ Wulai District, New Taipei City
    "66000100" to "M", // è‡ºä¸­å¸‚æ±å‹¢å€ Dongshi District, Taichung City
    "66000190" to "M", // è‡ºä¸­å¸‚æ–°ç¤¾å€ Xinshe District, Taichung City
    "66000200" to "M", // è‡ºä¸­å¸‚çŸ³å²¡å€ Shigang District, Taichung City
    "66000270" to "M", // è‡ºä¸­å¸‚å¤ªå¹³å€ Taiping District, Taichung City
    "66000290" to "M", // è‡ºä¸­å¸‚å’Œå¹³å€ Heping District, Taichung City
    "67000240" to "M", // è‡ºå—å¸‚æ¥ è¥¿å€ Nanxi District, Tainan City
    "67000250" to "M", // è‡ºå—å¸‚å—åŒ–å€ Nanhua District, Tainan City
    "68000130" to "M" // æ¡ƒåœ’å¸‚å¾©èˆˆå€ Fuxing District, Taoyuan City
)

// API endpoints for "Weather Assistant", a collection of human-written text-based
// forecast summary for the general public. "Assistants" are organized by counties.
// List of endpoints can be seen on this page:
// https://opendata.cwa.gov.tw/dataset/all?page=1
// Search for å¤©æ°£å°å¹«æ‰‹
val CWA_ASSISTANT_ENDPOINTS = mapOf<String, String>(
    "è‡ºåŒ—å¸‚" to "F-C0032-009", // Taipei City
    "æ–°åŒ—å¸‚" to "F-C0032-010", // New Taipei City
    "åŸºéš†å¸‚" to "F-C0032-011", // Keelung City
    "èŠ±è“®ç¸£" to "F-C0032-012", // Hualien County
    "å®œè˜­ç¸£" to "F-C0032-013", // Yilan County
    "é‡‘é–€ç¸£" to "F-C0032-014", // Kinmen County
    "æ¾Žæ¹–ç¸£" to "F-C0032-015", // Penghu County
    "è‡ºå—å¸‚" to "F-C0032-016", // Tainan City
    "é«˜é›„å¸‚" to "F-C0032-017", // Kaohsiung City
    "å˜‰ç¾©ç¸£" to "F-C0032-018", // Chiayi County
    "å˜‰ç¾©å¸‚" to "F-C0032-019", // Chiayi City
    "è‹—æ —ç¸£" to "F-C0032-020", // Miaoli County
    "è‡ºä¸­å¸‚" to "F-C0032-021", // Taichung City
    "æ¡ƒåœ’å¸‚" to "F-C0032-022", // Taoyuan City
    "æ–°ç«¹ç¸£" to "F-C0032-023", // Hsinchu County
    "æ–°ç«¹å¸‚" to "F-C0032-024", // Hsinchu City
    "å±æ±ç¸£" to "F-C0032-025", // Pingtung County
    "å—æŠ•ç¸£" to "F-C0032-026", // Nantou County
    "è‡ºæ±ç¸£" to "F-C0032-027", // Taitung County
    "å½°åŒ–ç¸£" to "F-C0032-028", // Changhua County
    "é›²æž—ç¸£" to "F-C0032-029", // Yunlin County
    "é€£æ±Ÿç¸£" to "F-C0032-030" // Lienchiang County
)

val CWA_HOURLY_ENDPOINTS = mapOf<String, String>(
    "å®œè˜­ç¸£" to "F-D0047-001", // Yilan County
    "æ¡ƒåœ’å¸‚" to "F-D0047-005", // Taoyuan City
    "æ–°ç«¹ç¸£" to "F-D0047-009", // Hsinchu County
    "è‹—æ —ç¸£" to "F-D0047-013", // Miaoli County
    "å½°åŒ–ç¸£" to "F-D0047-017", // Changhua County
    "å—æŠ•ç¸£" to "F-D0047-021", // Nantou County
    "é›²æž—ç¸£" to "F-D0047-025", // Yunlin County
    "å˜‰ç¾©ç¸£" to "F-D0047-029", // Chiayi County
    "å±æ±ç¸£" to "F-D0047-033", // Pingtung County
    "è‡ºæ±ç¸£" to "F-D0047-037", // Taitung County
    "èŠ±è“®ç¸£" to "F-D0047-041", // Hualien County
    "æ¾Žæ¹–ç¸£" to "F-D0047-045", // Penghu County
    "åŸºéš†å¸‚" to "F-D0047-049", // Keelung City
    "æ–°ç«¹å¸‚" to "F-D0047-053", // Hsinchu City
    "å˜‰ç¾©å¸‚" to "F-D0047-057", // Chiayi City
    "è‡ºåŒ—å¸‚" to "F-D0047-061", // Taipei City
    "é«˜é›„å¸‚" to "F-D0047-065", // Kaohsiung City
    "æ–°åŒ—å¸‚" to "F-D0047-069", // New Taipei City
    "è‡ºä¸­å¸‚" to "F-D0047-073", // Taichung City
    "è‡ºå—å¸‚" to "F-D0047-077", // Tainan City
    "é€£æ±Ÿç¸£" to "F-D0047-081", // Lienchiang County
    "é‡‘é–€ç¸£" to "F-D0047-085" // Kinmen County
)

val CWA_DAILY_ENDPOINTS = mapOf<String, String>(
    "å®œè˜­ç¸£" to "F-D0047-003", // Yilan County
    "æ¡ƒåœ’å¸‚" to "F-D0047-007", // Taoyuan City
    "æ–°ç«¹ç¸£" to "F-D0047-011", // Hsinchu County
    "è‹—æ —ç¸£" to "F-D0047-015", // Miaoli County
    "å½°åŒ–ç¸£" to "F-D0047-019", // Changhua County
    "å—æŠ•ç¸£" to "F-D0047-023", // Nantou County
    "é›²æž—ç¸£" to "F-D0047-027", // Yunlin County
    "å˜‰ç¾©ç¸£" to "F-D0047-031", // Chiayi County
    "å±æ±ç¸£" to "F-D0047-035", // Pingtung County
    "è‡ºæ±ç¸£" to "F-D0047-039", // Taitung County
    "èŠ±è“®ç¸£" to "F-D0047-043", // Hualien County
    "æ¾Žæ¹–ç¸£" to "F-D0047-047", // Penghu County
    "åŸºéš†å¸‚" to "F-D0047-051", // Keelung City
    "æ–°ç«¹å¸‚" to "F-D0047-055", // Hsinchu City
    "å˜‰ç¾©å¸‚" to "F-D0047-059", // Chiayi City
    "è‡ºåŒ—å¸‚" to "F-D0047-063", // Taipei City
    "é«˜é›„å¸‚" to "F-D0047-067", // Kaohsiung City
    "æ–°åŒ—å¸‚" to "F-D0047-071", // New Taipei City
    "è‡ºä¸­å¸‚" to "F-D0047-075", // Taichung City
    "è‡ºå—å¸‚" to "F-D0047-079", // Tainan City
    "é€£æ±Ÿç¸£" to "F-D0047-083", // Lienchiang County
    "é‡‘é–€ç¸£" to "F-D0047-087" // Kinmen County
)

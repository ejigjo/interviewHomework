用Maven建置的SpringBoot專案
使用Java 11
資料庫: MySQL

功能1:
提供一批次每日18:00呼叫API，取得外匯成交資料，並將每日的美元/台幣
欄位(USD/NTD)資料與日期(yyyy-MM-dd HH:mm:ss) insert 至 table/collection，
並針對批次功能寫Unit test。

a.為了代碼的復用性我建置接收API參數的物件我不只建置(USD/TWD)
如果未來要拓展不只要加入美元/台幣的數據可以重複使用

b.該接口設計每天會抓取API URL內最新的數據，為了節省空間以及去重，
我在MYSQL語句設計INSERT IGNORE INTO ，這樣就不會每天有重複的數據添加進去，只會增加最新的數據

功能2:
提供一forex API，從DB取出日期區間內美元/台幣的歷史資料，並針對API
功能寫Unit test。

a.為了代碼的復用性與方便性，設計return回前端API的工具類Result，只後每次return回前端時
只要調用工具類即可

b.因為調用數據是最頻繁的功能之一，特地設計兩個單元測試，分別是成功與失敗的測試
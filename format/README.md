# 日期（时间）的格式化
| Letter      | Date or Time Component                            | Presentation      | Examples                              |
| ----------- | -----------                                       | -----------       | -----------                           |
| G           | Era designator                                    | Text              | AD                                    |
| y           | Year                                              | Year              | 1996;96                               |
| M           | Month in year                                     | Month             | july;jul;07                           |
| w           | Week in year                                      | Number            | 27                                    |
| W           | Week in month                                     | Number            | 2                                     |
| D           | Day in year                                       | Number            | 189                                   |
| d           | Day in month                                      | Number            | 10                                    |
| F           | Day of week in month                              | Number            | 2                                     |
| E           | Dayn in week                                      | Text              | Tuesday; Tue                          |
| a           | Am/pm marker                                      | Text              | PM                                    |
| H           | Hour in day (0-23)                                | Number            | 0                                     |
| k           | Hour in day (1-24)                                | Number            | 24                                    |
| K           | Hour in am/pm(0-11)                               | Number            | 0                                     |
| h           | Hour in am/pm(1-12)                               | Number            | 12                                    |
| m           | Minute in hour                                    | Number            | 35                                    |
| s           | Second in minute                                  | Number            | 30                                    |
| S           | Millisecond                                       | Number            | 978                                   |
| z           | Time zone	                                        | General time zone | Pacific Standard Time; PST; GMT-08:00 |
| Z           | Time zone                                         | RFC 822 time zone | time zone	-0800                       |
表 1：date-time 模式[1]

## 参考文档
1. [rfc3339 Date and Time on the internet](https://www.ietf.org/rfc/rfc3339.txt)
2. [Formatting and parsing date-time patterns](https://www.ibm.com/docs/en/rtw/9.5?topic=reference-date-time-patterns)

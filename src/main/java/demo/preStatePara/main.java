//package demo.preStatePara;
//
//import org.apache.commons.lang.StringUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class main {
//    public static void main(String args[]){
//        Map head = new HashMap();
//        head.put("companyId", "2");
//        head.put("channel", "all");
//        head.put("icId", "007");
////        head.put("")
//
//        List<PreStatePara> paras = new ArrayList<>();
//        String res = getCAListSql2(head, paras).toString();
//
//        System.out.println(res);
//    }
//
//    public static StringBuffer getCAListSql2(Map<String, String> head, List<PreStatePara> paras) {
//        StringBuffer querySqlSb = new StringBuffer();
//        if (head == null) {
//            return querySqlSb;
//        }
//        if (paras == null) {
//            paras = new ArrayList<>();
//        }
//        int index = 1;
//
//
//        querySqlSb.append(
//                "select  decode(t.branchcode, 673, 'zj', 'zt') companyName,\n" +
//                        "                t.fundcode           fundCode,\n" +
//                        "                p.fundname           fundName,\n" +
//                        "                c.name               clientName,\n" +
//                        "                cr.score_id          scoreId,\n" +
//                        "                cr.score_memo        scoreMemo,\n" +
//                        "                t.acctid             acctId,\n" +
//                        "                t.productid          clientId,\n" +
//                        "                t.orderstatus        orderStatus,\n" +
//
//                        "                c.type               clientType,\n" +
//                        "                c.id_type            idType,\n" +
//                        "                c.id_no              idNo,\n" +
//                        "                p.product_inner_type productType,\n" +
//                        "                ta.taname            taName,\n" +
//                        "                p.fundmanagername    fundManagerName,\n" +
//                        "                'xxxxxxxx' AS fileTupe,\n" +
//                        "                t.occurtime  occurTime,\n" +
//                        "                p.tacode     taCode,\n" +
//                        "　　　　　　　　  case decode(aiis.appsheetserialno, null, 'GWMS', 'AIIS')\n" +
//                        "                  when 'GWMS' then\n" +
//                        "                    ch.order_id\n" +
//                        "                  when 'AIIS' then\n" +
//                        "                    aiis.appsheetserialno\n" +
//                        "                  else\n" +
//                        "                    ch.order_id\n" +
//                        "                end orderId," +
////                        "                ch.order_id       orderId,\n" +
//                        "                t.contractnum       contractNum,\n" +
//                        "                decode(aiis.appsheetserialno, null, 'GWMS', 'AIIS') channel," +
//                        "                c.main_company_id companyId\n" +
//                        "  from v_ofs_tradebusinessorderall@dblink_gwmsa t\n");
//
//
//
//
////        querySqlSb.append(" inner join ofs_fundinfo@dblink_gwmsa f\n" +
////                "        on t.fundcode=f.fundcode\n");
//
//        querySqlSb.append(" inner join cc_basic_info@dblink_gwmsa c\n" +
//                " on t.productid = c.client_id\n");
//
//        querySqlSb.append(" inner join ic_clientinfo icc\n" +
//                "    on t.productid = icc.client_id\n");
//
//        querySqlSb.append("  inner join v_ic_ic_map v\n" +
//                "    on v.icid = icc.ic_id\n" +
//                "   and v.ROOTICID = ? ");
//        paras.add(new PreStatePara(index++, head.get("icId")));
//
//        if (StringUtils.isNotBlank(head.get("entityId"))) {
//            querySqlSb.append(" inner join ic_icbranchmapview ici\n" +
//                    "    on icc.ic_id = ici.ICID\n" +
//                    "   and ici.BRANCHID = ?\n"
//            );
//            paras.add(new PreStatePara(index++, head.get("entityId")));
//        }
//
//        querySqlSb.append(" left join cc_orderhis@dblink_gwmsa ch\n " +
//                " on t.appsheetserialno = ch.appserial_num\n" +
//                " and ch.current_step_id = 'K'");
//
//        querySqlSb.append(" left join cc_client_rating_his@dblink_gwmsa cr\n" +
//                " on ch.user_risk_id = cr.entity_id " +
//                " and t.productid=cr.client_id");
//
//        querySqlSb.append(" left join v_pd_fundinfo@dblink_gwmsa p\n" +
//                " on t.fundcode = p.fundcode\n");
//
//        querySqlSb.append(" left join ofs_tainfo@dblink_gwmsa ta\n" +
//                " on p.tacode = ta.tacode\n");
//
//        querySqlSb.append(" left join v_aiis_order_his@dblink_gwmsa aiis\n" +
//                " on t.appsheetserialno=aiis.appsheetserialno\n" +
//                " and aiis.orderstatus='Settled' ");
//
//
//        querySqlSb.append(" where  " +
//                " t.businesscode in ('020','022') " +
//                " and t.portfoliotype != 6 "+
//                " and t.fundcode not in ('002829' , '005065') " +
//                " and t.fundcode not in ('002829' , '005065') " +
//                " and t.orderstatus <> 'Cancelled' " +
//                " and t.orderstatus <> 'Deduct_Failed'" +
//                " and t.occurtime>='20190101000000'");
//
//
//        if (StringUtils.isNotBlank(head.get("fundCode"))) {
//            querySqlSb.append(" and t.fundcode = ?  ");
//            paras.add(new PreStatePara(index++, head.get("fundCode")));
//        }
//        if (StringUtils.isNotBlank(head.get("acctId"))) {
//            querySqlSb.append(" and t.acctId = ?  ");
//            paras.add(new PreStatePara(index++, head.get("acctId")));
//        }
//
//        if (StringUtils.isNotBlank(head.get("beginDate"))) {
//            querySqlSb.append(" and t.occurtime >= ?  ");
//            paras.add(new PreStatePara(index++, head.get("beginDate")));
//        }
//
//        if (StringUtils.isNotBlank(head.get("endDate"))) {
//            querySqlSb.append(" and t.occurtime <= ?  ");
//            paras.add(new PreStatePara(index++, head.get("endDate")));
//        }
//        if (StringUtils.isNotBlank(head.get("companyId"))) {
//            if ("0".equals(head.get("companyId"))) {
//            } else {
//                querySqlSb.append(" and c.main_company_id= ? ");
//                paras.add(new PreStatePara(index++, head.get("companyId")));
//            }
//        }
//        if (StringUtils.isNotBlank(head.get("channel"))) {
//            if ("GWMS".equals(head.get("channel"))) {
//                querySqlSb.append(" and aiis.appsheetserialno is  null ");
//            } else if ("AIIS".equals(head.get("channel"))) {
//                querySqlSb.append(" and aiis.appsheetserialno is not null ");
//            } else {
//
//            }
//        }
//
//        querySqlSb.append(" order by t.occurtime desc");
//
//        return querySqlSb;
//
//
////        select * from v_aiis_order t
////        select * from v_aiis_order_his t order by t.updateTime desc;
//
//    }
//}

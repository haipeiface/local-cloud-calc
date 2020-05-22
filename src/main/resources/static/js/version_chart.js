function menu4_1() {
    var client = document.getElementById("first4_1").value;
    var bond_type_team_chart = echarts.init(document.getElementById('pieEchart4_1'));

    var bond_type_team_options = {};

    function bond_type_team_chart_ajax_feed() {
        $.ajax({
            type: "GET",
            dataType: "json",// 返回json格式的数据
            url: appPath + "/fiAnalysis/query4_1",
            data: {client:client},

            success: function (data) {

                console.log(data);

                bond_type_team_options = {
                    textStyle: {//全局字体
                        fontFamily: 'KaiTi'
                    },
                    title: {
                        text: '单个客户各类产品的中标量',
                        textStyle: {
                            fontSize: 19,
                            fontWeight: 'bolder'
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        },
                        iconStyle:{
                            normal:{
                                borderColor:'#8C9094',
                                borderWidth:1.5
                            },
                        }
                    },
                    series: [
                        {
                            name: '中标量',
                            type: 'pie',
                            radius: '35%',
                            center: ['50%', '50%'],
                            data: data,
                            itemStyle: {
                                normal: {
                                    label: {
                                        textStyle: {
                                            fontSize: 14,
                                            fontWeight: 'bolder',
                                            color: 'black'
                                        }
                                    }
                                }
                            }
                        }
                    ]


                };//options ending

                bond_type_team_chart.hideLoading();
                bond_type_team_chart.setOption(bond_type_team_options);

            }
        });
    }

    // bond_type_team_chart.showLoading();
    bond_type_team_chart_ajax_feed();



}

menu4_1();


<script>
import * as echarts from 'echarts';
import axios from 'axios';
import { v4 as uuidv4 } from 'uuid'
export default {
  data() {
    return {
      myChart: {},
      option:{},
      coords: [[0,0],[0,0]],
      coordsList: [],
      consumerUserIds:[],
      userId:""
    }
  },
  mounted() {
    this.getChart();
    //初始化websocket
    this.initWebSocket()
  },
  unmounted: function () { // 离开页面生命周期函数
    this.websocketclose();
  },
  methods: {
    getChart() {
      this.myChart = echarts.init(document.getElementById('main'));
      // this.mychart = myChart;
      const url = require('@/assets/airprot.svg')
      axios.get(url).then((response) => {
        echarts.registerMap('airport', {svg: response.data});
        this.option = {
          // title: {
          //   text: 'Visit Route',
          //   left: 'center',
          // },
          tooltip: {},
          geo: {
            map: 'airport',
            roam: false,
            layoutCenter: ['50%', '50%'],
            layoutSize: 750,
            emphasis: {
              itemStyle: {
                color: undefined
              },
              label: {
                show: false
              }
            }
          }
          ,
          series: [
            {
              name: 'Route',
              type: 'lines',
              coordinateSystem: 'geo',
              geoIndex: 0,
              emphasis: {
                label: {
                  show: false
                }
              },
              polyline: true,
              lineStyle: {
                color: '#c46e54',
                width: 5,
                opacity: 1,
                type: 'dotted'
              },
              // effect: {
              //   show: true,
              //   period: 8,
              //   color: '#a10000',
              //   // constantSpeed: 80,
              //   trailLength: 0,
              //   symbolSize: [12, 30],
              //   symbol:
              //       'path://M87.1667 3.8333L80.5.5h-60l-6.6667 3.3333L.5 70.5v130l10 10h80l10 -10v-130zM15.5 190.5l15 -20h40l15 20zm75 -65l-15 5v35l15 15zm-80 0l15 5v35l-15 15zm65 0l15 -5v-40l-15 20zm-50 0l-15 -5v-40l15 20zm 65,-55 -15,25 c -15,-5 -35,-5 -50,0 l -15,-25 c 25,-15 55,-15 80,0 z'
              // },
              data: [
                {
                  effect: {
                    color: '#997405',
                    constantSpeed: 100,
                    delay: 0
                  },
                  lineStyle: {
                    color: '#c46e54',
                    width: 4,
                    opacity: 1,
                    type: [5, 0]
                  },
                  coords: this.coords
                }
              ]
            }
          ]
        };
        this.myChart.setOption(this.option);
        // myChart.on('click', function (e) {
        //   let x = e.event.event.zrX;
        //   let y = e.event.event.zrY;
        //   console.log(myChart.convertFromPixel({seriesIndex: 0}, [x, y]));
        //   // const item = [x, y];
        //   // this.$data.coords.push(item);
        // });
      })
    },
    point(event) {
      const item = this.myChart.convertFromPixel({seriesIndex: 0}, [event.zrX, event.zrY]);
      console.log(item)
      if(this.coords.length === 0) {
        this.coords.push(item);
        this.coords.push(item);
      }else {
        this.coords.push(item);
      }
      this.coordsList.push({x : item[0], y: item[1]});

      // let option = this.myChart.getOption();
      this.option = {
        title: {
          text: 'Visit Route',
          left: 'center',
          bottom: 10
        },
        tooltip: {},
        geo: {
          map: 'airport',
          roam: false,
          layoutCenter: ['50%', '50%'],
          layoutSize: 750,
          emphasis: {
            itemStyle: {
              color: undefined
            },
            label: {
              show: false
            }
          }
        },
        series: [
          {
            name: 'Route',
            type: 'lines',
            coordinateSystem: 'geo',
            geoIndex: 0,
            emphasis: {
              label: {
                show: false
              }
            },
            polyline: true,
            lineStyle: {
              color: '#c46e54',
              width: 5,
              opacity: 1,
              type: 'dotted'
            },
            // effect: {
            //   show: true,
            //   period: 8,
            //   color: '#a10000',
            //   // constantSpeed: 80,
            //   trailLength: 0,
            //   symbolSize: [12, 30],
            //   symbol:
            //       'path://M87.1667 3.8333L80.5.5h-60l-6.6667 3.3333L.5 70.5v130l10 10h80l10 -10v-130zM15.5 190.5l15 -20h40l15 20zm75 -65l-15 5v35l15 15zm-80 0l15 5v35l-15 15zm65 0l15 -5v-40l-15 20zm-50 0l-15 -5v-40l15 20zm 65,-55 -15,25 c -15,-5 -35,-5 -50,0 l -15,-25 c 25,-15 55,-15 80,0 z'
            // },
            data: [
              {
                effect: {
                  color: '#997405',
                  constantSpeed: 100,
                  delay: 0
                },
                lineStyle: {
                  color: '#c46e54',
                  width: 4,
                  opacity: 1,
                  type: [5, 0]
                },
                coords: this.coords
              }
            ]
          }
        ]
      };
      this.myChart.setOption(this.option);
    },

    drawLine() {
      const requestBody = {
        macAddr: this.userId,
        coordsList : this.coordsList
      };

      axios.post('/api/draw/drawLine', requestBody).then(response => {
        console.log(response.data);
      }).catch(error => {
        console.log("error:" + error);
      })
    },

    initWebSocket() {
      // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
      //  const userId = '10001';
      const userId = uuidv4()
      var url = "ws://localhost:8080/producerwebsocket/"+userId;
      this.websock = new WebSocket(url);
      this.websock.onopen = this.websocketonopen;
      this.websock.send = this.websocketsend;
      this.websock.onerror = this.websocketonerror;
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onclose = this.websocketclose;
    },
    // 连接成功后调用
    websocketonopen: function () {
      console.log("WebSocket连接成功");
    },
    // 发生错误时调用
    websocketonerror: function () {
      console.log("WebSocket连接发生错误");
    },
    // 给后端发消息时调用
    websocketsend: function () {
      console.log("WebSocket连接发生错误");
    },
    // 接收后端消息
    // vue 客户端根据返回的cmd类型处理不同的业务响应
    websocketonmessage: function (e) {
      let data = eval("(" + e.data + ")");
      this.consumerUserIds = data;
      console.log(data);
    },
    // 关闭连接时调用
    websocketclose: function (e) {
      console.log("connection closed (" + e.code + ")");
    }
  }
}
</script>
<template>
  <input v-model="userId" />
  <button @click="drawLine">提交线路</button>
  <menu>
    <li v-for="(item, index) in consumerUserIds" :key="index">
      {{item}}
    </li>
  </menu>
  <div id="main" @click="point"></div>
<!--  < img :src="svg">-->
</template>
<style scoped>
#main{
  width: 800px;
  height: 800px;
}
</style>
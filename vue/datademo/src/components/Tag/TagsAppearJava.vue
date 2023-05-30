<template>
  <div id="chart-container" style="width: 80vw; height: 80vh"/>
</template>

<script>
import "echarts-wordcloud";
export default {
  name: "TagsAppearJava",
  data(){
    return{
      chart: null,
    }
  },
  mounted() {
    this.initChart();
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      this.chart = this.$echarts.init(document.getElementById("chart-container"));
      var keywords = [];
      for (let index = 0; index < 20; index++) {
        let random = Math.floor(Math.random() * 100);
        keywords.push({
          name: random,
          value: random,
        });
      }
      var option = {
        series: [
          {
            type: "wordCloud",
            sizeRange: [12, 60],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 15,
            shape: "pentagon",
            width: "100%",
            height: "100%",
            drawOutOfBound: true,
            textStyle: {
              color: function () {
                return (
                    "rgb(" +
                    [
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                      Math.round(Math.random() * 160),
                    ].join(",") +
                    ")"
                );
              },
            },
            emphasis: {
              textStyle: {
                shadowBlur: 10,
                shadowColor: "#333",
                color: "red",
              },
            },
            data: keywords,
          },
        ],
      };
      this.chart.setOption(option);
    },
  },
}
</script>

<style scoped>

</style>
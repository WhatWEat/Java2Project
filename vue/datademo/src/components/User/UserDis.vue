<template>
  <div id="UserDis" style="width: 80vw; height: 80vh"/>
</template>

<script>
import axios from "axios";

export default {
  name: "UserDis",
  methods:{
    getData(){
      axios.get('/Users/Q2').then(res => {
        console.log(res.data);
      })
    }
  },
  mounted() {
    this.getData();
  }
}
</script>

<style scoped>

</style>
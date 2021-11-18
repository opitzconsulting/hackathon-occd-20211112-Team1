<template>
  <div id="app">
    <vue-data-table></vue-data-table>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import VueDataTable from "@/components/VueDataTable.vue";


@Options({
  components: {
    VueDataTable
  },
})
export default class App extends Vue {
  name = 'app';
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}
</style>

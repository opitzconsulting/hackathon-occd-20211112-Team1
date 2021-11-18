<template>
  <div class="table-container">
    <h2>Wallbox Consumption Data Overview</h2>
    <DataTable :value="chargingSessions" dataKey="id">
      <Column field="idTag" header="ID-Tag" sortable="true" class="charging-session-header-title"
              header-class="charging-session-header">
      </Column>
      <Column field="transactionId" header="Transaction-ID" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
      <Column field="startMeter" header="Start Meter" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
      <Column field="stopMeter" header="Stop Meter" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
      <Column field="consumption" header="Consumption" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
      <Column field="chargingStart" header="Charging Start" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
      <Column field="chargingEnd" header="Charging End" sortable="true"
              class="charging-session-header-title"
              header-class="charging-session-header"></Column>
    </DataTable>
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component';
import axios from "axios";
import {ChargingSession} from "@/model/ChargingSession";

export default class VueDataTable extends Vue {
  chargingSessions!: ChargingSession[];
  data() {
    return {
      chargingSessions: null
    }
  }

  async created() {
    this.chargingSessions = await axios.get("http://localhost:8080/gui/api/v1/consumption/all")
        .then(res => this.chargingSessions = res.data);
  }
}
</script>

<style lang="css">
  .table-container {
    text-align: left;
    font-family: 'Helvetica Neue', 'Helvetica', 'Arial', sans-serif;
    margin: 10px 10px 10px 10px;
  }

  h2 {
    padding-bottom: 1em;
  }

  thead.p-datatable-thead {
    background-color: #ECEFF4;
  }

  .charging-session-header {
    height: 2em;
    border-bottom: 1px solid #C0D0E0;
  }

  .charging-session-header-title {
    height: 2em;
    border-bottom: 1px solid #C0D0E0;
  }

  tr:nth-child(even) {
    background-color: #DAE4F0;
  }
</style>

<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <monitoringPoint-table
            v-if="monitoringPoints && monitoringPoints.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:monitoringPoints="monitoringPoints"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-monitoring-points="getAllMonitoringPoints"
             >

            </monitoringPoint-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MonitoringPointTable from "@/components/MonitoringPointTable";
import MonitoringPointService from "../services/MonitoringPointService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MonitoringPointTable,
  },
  data() {
    return {
      monitoringPoints: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllMonitoringPoints(sortBy='monitoringPointId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MonitoringPointService.getAllMonitoringPoints(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.monitoringPoints.length) {
					this.monitoringPoints = response.data.monitoringPoints;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching monitoringPoints:", error);
        }
        
      } catch (error) {
        console.error("Error fetching monitoringPoint details:", error);
      }
    },
  },
  mounted() {
    this.getAllMonitoringPoints();
  },
  created() {
    this.$root.$on('searchQueryForMonitoringPointsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMonitoringPoints();
    })
  }
};
</script>
<style></style>

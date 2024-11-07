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
            <maintenanceLog-table
            v-if="maintenanceLogs && maintenanceLogs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:maintenanceLogs="maintenanceLogs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-maintenance-logs="getAllMaintenanceLogs"
             >

            </maintenanceLog-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MaintenanceLogTable from "@/components/MaintenanceLogTable";
import MaintenanceLogService from "../services/MaintenanceLogService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MaintenanceLogTable,
  },
  data() {
    return {
      maintenanceLogs: [],
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
    async getAllMaintenanceLogs(sortBy='maintenanceLogId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MaintenanceLogService.getAllMaintenanceLogs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.maintenanceLogs.length) {
					this.maintenanceLogs = response.data.maintenanceLogs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching maintenanceLogs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching maintenanceLog details:", error);
      }
    },
  },
  mounted() {
    this.getAllMaintenanceLogs();
  },
  created() {
    this.$root.$on('searchQueryForMaintenanceLogsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMaintenanceLogs();
    })
  }
};
</script>
<style></style>

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
            <alert-table
            v-if="alerts && alerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:alerts="alerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-alerts="getAllAlerts"
             >

            </alert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AlertTable from "@/components/AlertTable";
import AlertService from "../services/AlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AlertTable,
  },
  data() {
    return {
      alerts: [],
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
    async getAllAlerts(sortBy='alertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AlertService.getAllAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.alerts.length) {
					this.alerts = response.data.alerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching alerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching alert details:", error);
      }
    },
  },
  mounted() {
    this.getAllAlerts();
  },
  created() {
    this.$root.$on('searchQueryForAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAlerts();
    })
  }
};
</script>
<style></style>

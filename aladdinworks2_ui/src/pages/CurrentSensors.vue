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
            <currentSensor-table
            v-if="currentSensors && currentSensors.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:currentSensors="currentSensors"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-current-sensors="getAllCurrentSensors"
             >

            </currentSensor-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CurrentSensorTable from "@/components/CurrentSensorTable";
import CurrentSensorService from "../services/CurrentSensorService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CurrentSensorTable,
  },
  data() {
    return {
      currentSensors: [],
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
    async getAllCurrentSensors(sortBy='currentSensorId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CurrentSensorService.getAllCurrentSensors(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.currentSensors.length) {
					this.currentSensors = response.data.currentSensors;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching currentSensors:", error);
        }
        
      } catch (error) {
        console.error("Error fetching currentSensor details:", error);
      }
    },
  },
  mounted() {
    this.getAllCurrentSensors();
  },
  created() {
    this.$root.$on('searchQueryForCurrentSensorsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCurrentSensors();
    })
  }
};
</script>
<style></style>

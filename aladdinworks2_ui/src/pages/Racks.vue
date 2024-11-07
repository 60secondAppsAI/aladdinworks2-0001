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
            <rack-table
            v-if="racks && racks.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:racks="racks"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-racks="getAllRacks"
             >

            </rack-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RackTable from "@/components/RackTable";
import RackService from "../services/RackService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RackTable,
  },
  data() {
    return {
      racks: [],
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
    async getAllRacks(sortBy='rackId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RackService.getAllRacks(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.racks.length) {
					this.racks = response.data.racks;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching racks:", error);
        }
        
      } catch (error) {
        console.error("Error fetching rack details:", error);
      }
    },
  },
  mounted() {
    this.getAllRacks();
  },
  created() {
    this.$root.$on('searchQueryForRacksChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRacks();
    })
  }
};
</script>
<style></style>

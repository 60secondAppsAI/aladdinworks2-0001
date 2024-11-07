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
            <switch-table
            v-if="switchs && switchs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:switchs="switchs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-switchs="getAllSwitchs"
             >

            </switch-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SwitchTable from "@/components/SwitchTable";
import SwitchService from "../services/SwitchService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SwitchTable,
  },
  data() {
    return {
      switchs: [],
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
    async getAllSwitchs(sortBy='switchId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SwitchService.getAllSwitchs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.switchs.length) {
					this.switchs = response.data.switchs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching switchs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching switch details:", error);
      }
    },
  },
  mounted() {
    this.getAllSwitchs();
  },
  created() {
    this.$root.$on('searchQueryForSwitchsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSwitchs();
    })
  }
};
</script>
<style></style>

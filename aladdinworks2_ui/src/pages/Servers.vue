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
            <server-table
            v-if="servers && servers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:servers="servers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-servers="getAllServers"
             >

            </server-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ServerTable from "@/components/ServerTable";
import ServerService from "../services/ServerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ServerTable,
  },
  data() {
    return {
      servers: [],
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
    async getAllServers(sortBy='serverId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ServerService.getAllServers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.servers.length) {
					this.servers = response.data.servers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching servers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching server details:", error);
      }
    },
  },
  mounted() {
    this.getAllServers();
  },
  created() {
    this.$root.$on('searchQueryForServersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllServers();
    })
  }
};
</script>
<style></style>

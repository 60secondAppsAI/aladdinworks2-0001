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
            <networkDevice-table
            v-if="networkDevices && networkDevices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:networkDevices="networkDevices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-network-devices="getAllNetworkDevices"
             >

            </networkDevice-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import NetworkDeviceTable from "@/components/NetworkDeviceTable";
import NetworkDeviceService from "../services/NetworkDeviceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    NetworkDeviceTable,
  },
  data() {
    return {
      networkDevices: [],
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
    async getAllNetworkDevices(sortBy='networkDeviceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await NetworkDeviceService.getAllNetworkDevices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.networkDevices.length) {
					this.networkDevices = response.data.networkDevices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching networkDevices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching networkDevice details:", error);
      }
    },
  },
  mounted() {
    this.getAllNetworkDevices();
  },
  created() {
    this.$root.$on('searchQueryForNetworkDevicesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllNetworkDevices();
    })
  }
};
</script>
<style></style>

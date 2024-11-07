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
            <coolingUnit-table
            v-if="coolingUnits && coolingUnits.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:coolingUnits="coolingUnits"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cooling-units="getAllCoolingUnits"
             >

            </coolingUnit-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CoolingUnitTable from "@/components/CoolingUnitTable";
import CoolingUnitService from "../services/CoolingUnitService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CoolingUnitTable,
  },
  data() {
    return {
      coolingUnits: [],
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
    async getAllCoolingUnits(sortBy='coolingUnitId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CoolingUnitService.getAllCoolingUnits(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.coolingUnits.length) {
					this.coolingUnits = response.data.coolingUnits;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching coolingUnits:", error);
        }
        
      } catch (error) {
        console.error("Error fetching coolingUnit details:", error);
      }
    },
  },
  mounted() {
    this.getAllCoolingUnits();
  },
  created() {
    this.$root.$on('searchQueryForCoolingUnitsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCoolingUnits();
    })
  }
};
</script>
<style></style>

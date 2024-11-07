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
            <equipment-table
            v-if="equipments && equipments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:equipments="equipments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-equipments="getAllEquipments"
             >

            </equipment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import EquipmentTable from "@/components/EquipmentTable";
import EquipmentService from "../services/EquipmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    EquipmentTable,
  },
  data() {
    return {
      equipments: [],
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
    async getAllEquipments(sortBy='equipmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await EquipmentService.getAllEquipments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.equipments.length) {
					this.equipments = response.data.equipments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching equipments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching equipment details:", error);
      }
    },
  },
  mounted() {
    this.getAllEquipments();
  },
  created() {
    this.$root.$on('searchQueryForEquipmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllEquipments();
    })
  }
};
</script>
<style></style>

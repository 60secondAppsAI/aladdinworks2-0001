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
            <generator-table
            v-if="generators && generators.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:generators="generators"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-generators="getAllGenerators"
             >

            </generator-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import GeneratorTable from "@/components/GeneratorTable";
import GeneratorService from "../services/GeneratorService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    GeneratorTable,
  },
  data() {
    return {
      generators: [],
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
    async getAllGenerators(sortBy='generatorId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await GeneratorService.getAllGenerators(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.generators.length) {
					this.generators = response.data.generators;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching generators:", error);
        }
        
      } catch (error) {
        console.error("Error fetching generator details:", error);
      }
    },
  },
  mounted() {
    this.getAllGenerators();
  },
  created() {
    this.$root.$on('searchQueryForGeneratorsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllGenerators();
    })
  }
};
</script>
<style></style>

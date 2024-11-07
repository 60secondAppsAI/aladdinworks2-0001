
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Power Strips</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalPowerStrips = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalPowerStrips">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add PowerStrip</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="PowerStripId" type="text" placeholder="Enter PowerStripId" v-model="powerStripToAdd.powerStripId"></base-input>
  <base-input label="NumberOfOutlets" type="text" placeholder="Enter NumberOfOutlets" v-model="powerStripToAdd.numberOfOutlets"></base-input>
  <base-input label="Location" type="text" placeholder="Enter Location" v-model="powerStripToAdd.location"></base-input>
  <base-input label="Status" type="text" placeholder="Enter Status" v-model="powerStripToAdd.status"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="powerStrips" :row-key="record => record.PowerStripId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <PowerStripPictureView :powerStrips="powerStrips" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import PowerStripService from "../services/PowerStripService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import PowerStripPictureView from './PowerStripPictureView.vue';


const powerStripsColumns = [
  "powerStripId",
  "year",
  "date",
  "competitionId",
  "powerStripId"
]

export default {
  props: {
    powerStrips: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    PowerStripPictureView
  },

  data() {
    return {
      modalPowerStrips: false,
        isTableView: true,

      columns: [
        {
          title: 'Power Strip Id',
		dataIndex: 'powerStripId',
          visible: true,
          scopedSlots: { customRender: 'powerStripId' },
          sorter: true
          //sorter: (a, b) => a.powerStripId - b.powerStripId,
          //sorter: (a, b) => a.powerStripId.localeCompare(b.powerStripId),
        },
        {
          title: 'Number Of Outlets',
		dataIndex: 'numberOfOutlets',
          visible: true,
          scopedSlots: { customRender: 'numberOfOutlets' },
          sorter: true
          //sorter: (a, b) => a.numberOfOutlets - b.numberOfOutlets,
          //sorter: (a, b) => a.numberOfOutlets.localeCompare(b.numberOfOutlets),
        },
        {
          title: 'Location',
		dataIndex: 'location',
          visible: true,
          scopedSlots: { customRender: 'location' },
          sorter: true
          //sorter: (a, b) => a.location - b.location,
          //sorter: (a, b) => a.location.localeCompare(b.location),
        },
        {
          title: 'Status',
		dataIndex: 'status',
          visible: true,
          scopedSlots: { customRender: 'status' },
          sorter: true
          //sorter: (a, b) => a.status - b.status,
          //sorter: (a, b) => a.status.localeCompare(b.status),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} powerStrips`,
      },

      powerStrips: [],
      powerStripToAdd: {},

      powerStripsTable: {
        columns: [...powerStripsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'powerStripId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderPowerStripsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let powerStripsTableData = [];
      for (let i = 0; i < this.powerStrips.length; i++) {
        powerStripsTableData.push({
          id: i,
          powerStripId: this.powerStrips[i].powerStripId,
          year: this.powerStrips[i].year,
          date: this.powerStrips[i].date,
          competitionId: this.powerStrips[i].competitionId,
          powerStripId: this.powerStrips[i].powerStripId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-power-strips',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToDataCenterDetail(id) {
      this.$router.push({ name: 'DataCenterDetail', params: { dataCenterId: id.toString() }})
    },
    routingToRackDetail(id) {
      this.$router.push({ name: 'RackDetail', params: { rackId: id.toString() }})
    },
    routingToServerDetail(id) {
      this.$router.push({ name: 'ServerDetail', params: { serverId: id.toString() }})
    },
    routingToSwitchDetail(id) {
      this.$router.push({ name: 'SwitchDetail', params: { switchId: id.toString() }})
    },
    routingToCoolingUnitDetail(id) {
      this.$router.push({ name: 'CoolingUnitDetail', params: { coolingUnitId: id.toString() }})
    },
    routingToGeneratorDetail(id) {
      this.$router.push({ name: 'GeneratorDetail', params: { generatorId: id.toString() }})
    },
    routingToPowerSupplyDetail(id) {
      this.$router.push({ name: 'PowerSupplyDetail', params: { powerSupplyId: id.toString() }})
    },
    routingToPowerStripDetail(id) {
      this.$router.push({ name: 'PowerStripDetail', params: { powerStripId: id.toString() }})
    },
    routingToMonitoringPointDetail(id) {
      this.$router.push({ name: 'MonitoringPointDetail', params: { monitoringPointId: id.toString() }})
    },
    routingToTemperatureSensorDetail(id) {
      this.$router.push({ name: 'TemperatureSensorDetail', params: { temperatureSensorId: id.toString() }})
    },
    routingToCurrentSensorDetail(id) {
      this.$router.push({ name: 'CurrentSensorDetail', params: { currentSensorId: id.toString() }})
    },
    routingToNetworkDeviceDetail(id) {
      this.$router.push({ name: 'NetworkDeviceDetail', params: { networkDeviceId: id.toString() }})
    },
    routingToMaintenanceLogDetail(id) {
      this.$router.push({ name: 'MaintenanceLogDetail', params: { maintenanceLogId: id.toString() }})
    },
    routingToAlertDetail(id) {
      this.$router.push({ name: 'AlertDetail', params: { alertId: id.toString() }})
    },
    routingToRackAssetDetail(id) {
      this.$router.push({ name: 'RackAssetDetail', params: { rackAssetId: id.toString() }})
    },
    routingToEquipmentDetail(id) {
      this.$router.push({ name: 'EquipmentDetail', params: { equipmentId: id.toString() }})
    },
    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForPowerStripsChanged', this.searchQuery);
		//this.renderPowerStripsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalPowerStrips = false;

      const currentDate = new Date().getTime();
      this.powerStripToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.powerStripToAdd);
      console.log(jsonData);
      
      const res = await PowerStripService.addPowerStrip(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderPowerStripsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>

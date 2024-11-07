<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/dataCenters">
						<span class="label">Data Centers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/racks">
						<span class="label">Racks</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/servers">
						<span class="label">Servers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/switchs">
						<span class="label">Switchs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/coolingUnits">
						<span class="label">Cooling Units</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/generators">
						<span class="label">Generators</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/powerSupplys">
						<span class="label">Power Supplys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/powerStrips">
						<span class="label">Power Strips</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/monitoringPoints">
						<span class="label">Monitoring Points</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/temperatureSensors">
						<span class="label">Temperature Sensors</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/currentSensors">
						<span class="label">Current Sensors</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/networkDevices">
						<span class="label">Network Devices</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/maintenanceLogs">
						<span class="label">Maintenance Logs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/alerts">
						<span class="label">Alerts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/rackAssets">
						<span class="label">Rack Assets</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/equipments">
						<span class="label">Equipments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/users">
						<span class="label">Users</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>


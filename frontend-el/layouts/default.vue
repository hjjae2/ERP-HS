<template>
  <v-app>
    <common-header />
    <common-navigator />
    <nuxt />
    <common-footer />
  </v-app>
</template>

<script>
import CommonHeader from '~/layouts/header.vue'
import CommonNavigator from '~/layouts/navigator.vue'
import CommonFooter from '~/layouts/footer.vue'
export default {
  components: {
    CommonHeader,
    CommonNavigator,
    CommonFooter,
  },
}
</script>

<style scoped></style>

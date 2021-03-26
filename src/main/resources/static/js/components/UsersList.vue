<template>
    <v-container fluid>
        <v-row align="center">
<!--            <v-col cols="6">-->
<!--                <v-subheader>-->
<!--                    Prepended icon-->
<!--                </v-subheader>-->
<!--            </v-col>-->
<!--            <v-col cols="6">-->
<!--                <v-select-->
<!--                        v-model="e1"-->
<!--                        :items="states"-->
<!--                        menu-props="auto"-->
<!--                        label="Select"-->
<!--                        hide-details-->
<!--                        prepend-icon="mdi-map"-->
<!--                        single-line-->
<!--                ></v-select>-->
<!--            </v-col>-->
            <v-col cols="6">
                <v-subheader>
                    Find User:
                </v-subheader>
            </v-col>
            <v-col cols="4">
                <v-select
                        v-model="e2"
                        :items="getAllUser"
                        :item-text="'name'"

                        append-outer-icon="mdi-map"
                        menu-props="auto"
                        hide-details
                        label="Select"
                        single-line

                >
                    <template #selected-option-container="{ option, deselect, multiple, disabled }">
                        <div class="vs__selected">{{ option.id }}</div>
                    </template>

                </v-select>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    export default {
        name: "UsersList",
        computed: mapGetters(['getAllUser']),


        // computed: {
        //     getAllUser(){return  this.$store.getters.getAllUser}
        // }

    }

</script>

<style scoped>

</style>
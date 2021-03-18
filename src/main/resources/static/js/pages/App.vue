<template>
    <v-app app>
        <v-app-bar app color="indigo">
           <span v-if="profile">  {{ profile.name }}</span>
            <v-spacer></v-spacer>
            <v-btn icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
<!--            <v-container v-if="!profile"> Please login-->
<!--                    <a href="/login">Google</a>-->
<!--                </v-container>-->
<!--                <v-container v-if="profile">-->
<!--                    <messages-list />-->
<!--                </v-container>-->
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from "util/ws";


    export default {
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation','updateMessageMutation','removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE' ) {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Event type not found "${data.eventType}"`)
                    }
                }else {
                    console.error(`Object type not found "${data.objectType}"`)
                }
            })
        }
    }
</script>

<style scoped>

</style>
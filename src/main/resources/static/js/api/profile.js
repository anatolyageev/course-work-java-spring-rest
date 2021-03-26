import Vue from 'vue'

const profile = Vue.resource('/profile{/id}')

export default {
    get: id => profile.get({id}),
    getAll: id => profile.getAlll(),
    changeSubscription: channelId => Vue.http.post(`/profile/change-subscription/${channelId}`),
    subscriberList: channelId => Vue.http.get(`/profile/get-subscribers/${channelId}`),
    changeSubscriptionStatus: subscriberId => Vue.http.post(`/profile/change-status/${subscriberId}`)
}
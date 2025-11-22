import {apiSlice} from "../slice/apiSlice";

export const pointsApi = apiSlice.injectEndpoints({
    endpoints: (build) => ({
        getPoints: build.query({
            query: () => ({
                url: '/points',
                method: 'GET'
            }),
            providesTags: ['Points']
        }),
        addPoint: build.mutation({
            query: (coordinates) => ({
                url: '/points',
                method: 'POST',
                body: {...coordinates}
            }),
            invalidatesTags: ['Points']
        })
    })
});
export const {
    useGetPointsQuery,
    useAddPointMutation
} = pointsApi;


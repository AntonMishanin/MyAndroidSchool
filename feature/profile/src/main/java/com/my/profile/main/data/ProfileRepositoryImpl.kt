package com.my.profile.main.data

import com.my.profile.main.data.converter.ProfileToDomainConverter
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.repository.ProfileRepository
import io.reactivex.Flowable

class ProfileRepositoryImpl(
    private val dataSource: MockRemoteDataSource,
    private val toDomainConverter: ProfileToDomainConverter
) : ProfileRepository {

    override fun fetchFeatures(): Flowable<List<ProfileFeature>> =
        dataSource.fetchFeatures().map { toDomainConverter.convert(it) }.toFlowable()
}
package com.zwartzon.domain.user.core.ports.incoming;

import com.zwartzon.domain.user.core.model.GetUserByHandleQuery;
import com.zwartzon.domain.user.core.model.User;

public interface GetUserByHandlePort {
  User handle(GetUserByHandleQuery query);
}

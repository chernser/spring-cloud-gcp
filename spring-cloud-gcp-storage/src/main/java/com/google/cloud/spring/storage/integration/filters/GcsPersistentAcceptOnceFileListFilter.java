/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.storage.integration.filters;

import com.google.cloud.storage.BlobInfo;
import org.springframework.integration.file.filters.AbstractPersistentAcceptOnceFileListFilter;
import org.springframework.integration.metadata.ConcurrentMetadataStore;

/** A filter for Google Cloud Storage. */
public class GcsPersistentAcceptOnceFileListFilter
    extends AbstractPersistentAcceptOnceFileListFilter<BlobInfo> {

  public GcsPersistentAcceptOnceFileListFilter(ConcurrentMetadataStore store, String prefix) {
    super(store, prefix);
  }

  @Override
  protected long modified(BlobInfo blobInfo) {
    return (blobInfo != null && blobInfo.getUpdateTime() != null) ? blobInfo.getUpdateTime() : -1;
  }

  @Override
  protected String fileName(BlobInfo blobInfo) {
    return (blobInfo != null) ? blobInfo.getName() : null;
  }

  @Override
  protected boolean isDirectory(BlobInfo blobInfo) {
    return blobInfo.isDirectory();
  }
}
